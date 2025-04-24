package tech.drufontael.carshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.drufontael.carshop.documentation.ConsignmentDoc;
import tech.drufontael.carshop.dto.ConsignmentDto;
import tech.drufontael.carshop.services.ConsignmentService;

import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("api/consignments")
public class ConsignmentController implements ConsignmentDoc {

    @Autowired
    private ConsignmentService service;

    @PostMapping
    public ResponseEntity<ConsignmentDto> create(@RequestBody ConsignmentDto consignment) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(consignment));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsignmentDto> findByRegister(@PathVariable UUID id) {
        var consignment=service.read(id);
        /*consignment.add(linkTo(methodOn(ConsignmentController.class)
                .findAll()).withRel("Consignments list"));*/
        return ResponseEntity.ok(consignment);
    }

    @GetMapping
    public ResponseEntity<List<ConsignmentDto>> findAll() {
        var list=service.findAll();
       /* for(ConsignmentDto consignment:list){
            consignment.add(linkTo(methodOn(ConsignmentController.class)
                    .findByRegister(consignment.getId()))
                    .withSelfRel());
        }*/
        return ResponseEntity.ok(list);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsignmentDto> update(@PathVariable UUID id, @RequestBody ConsignmentDto obj) {
        var updateConsignment=service.update(id,obj);
        updateConsignment.add(linkTo(methodOn(ConsignmentController.class).findAll()).withRel("Consignments list"));
        return ResponseEntity.ok(updateConsignment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<ConsignmentDto>> findByConsignorRegister(String register) {
        return null;
    }

    @Override
    public ResponseEntity<ConsignmentDto> findByVehiclePlate(String plate) {
        return null;
    }

    @GetMapping("/pdfContract/{id}")
    public ResponseEntity<ByteArrayResource> downloadPDF(@PathVariable UUID id) {
         byte[] pdfContent = service.createPdfContract(id);


        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=arquivo.pdf");


        ByteArrayResource resource = new ByteArrayResource(pdfContent);

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .contentLength(pdfContent.length)
                .body(resource);
    }
}
