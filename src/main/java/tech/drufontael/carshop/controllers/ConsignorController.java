package tech.drufontael.carshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tech.drufontael.carshop.documentation.ConsignorDoc;
import tech.drufontael.carshop.dto.ConsignorDto;
import tech.drufontael.carshop.model.Consignor;
import tech.drufontael.carshop.services.ConsignorService;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/consignors")
public class ConsignorController implements ConsignorDoc {

    @Autowired
    private ConsignorService service;

    @PostMapping
    public ResponseEntity<ConsignorDto> create(@RequestBody ConsignorDto consignor) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(consignor));
    }

    @GetMapping("/{register}")
    public ResponseEntity<ConsignorDto> findByRegister(@PathVariable String register) {
        var consignor=service.read(register);
        consignor.add(linkTo(methodOn(ConsignorController.class)
                .findAll()).withRel("Consignors list"));
        return ResponseEntity.ok(consignor);
    }

    @GetMapping
    public ResponseEntity<List<ConsignorDto>> findAll() {
        var list=service.findAll();
        for(ConsignorDto consignor:list){
            consignor.add(linkTo(methodOn(ConsignorController.class)
                    .findByRegister(consignor.getRegister()))
                    .withSelfRel());
        }
        return ResponseEntity.ok(list);
    }

    @PutMapping("/{register}")
    public ResponseEntity<ConsignorDto> update(@PathVariable String register, @RequestBody ConsignorDto obj) {
        var updateConsignor=service.update(register,obj);
        updateConsignor.add(linkTo(methodOn(ConsignorController.class).findAll()).withRel("Consignors list"));
        return ResponseEntity.ok(updateConsignor);
    }

    @DeleteMapping("/{register}")
    public ResponseEntity delete(@PathVariable String register) {
        service.delete(register);
        return ResponseEntity.noContent().build();
    }
}
