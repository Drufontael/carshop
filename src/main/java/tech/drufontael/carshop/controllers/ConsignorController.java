package tech.drufontael.carshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tech.drufontael.carshop.documentation.ConsignorDoc;
import tech.drufontael.carshop.dto.ConsignorDto;
import tech.drufontael.carshop.services.ConsignorService;

import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("api/consignors")
public class ConsignorController implements ConsignorDoc {

    @Autowired
    private ConsignorService service;

    @PostMapping
    public ResponseEntity<ConsignorDto> create(@RequestBody ConsignorDto consignor) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(consignor));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsignorDto> findById(@PathVariable UUID id) {
        var consignor=service.read(id);
        consignor.add(linkTo(methodOn(ConsignorController.class)
                .findAll()).withRel("ConsignorsList"));
        return ResponseEntity.ok(consignor);
    }

    @GetMapping
    public ResponseEntity<List<ConsignorDto>> findAll() {
        var list=service.findAll();
        for(ConsignorDto consignor:list){
            consignor.add(linkTo(methodOn(ConsignorController.class)
                    .findById(consignor.getId()))
                    .withSelfRel());
        }
        return ResponseEntity.ok(list);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsignorDto> update(@PathVariable UUID id, @RequestBody ConsignorDto obj) {
        var updateConsignor=service.update(id,obj);
        updateConsignor.add(linkTo(methodOn(ConsignorController.class).findAll()).withRel("ConsignorsList"));
        return ResponseEntity.ok(updateConsignor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
