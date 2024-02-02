package tech.drufontael.carshop.controllers;


import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.drufontael.carshop.documentation.VehicleDoc;
import tech.drufontael.carshop.dto.ImageDto;
import tech.drufontael.carshop.dto.VehicleDto;
import tech.drufontael.carshop.model.Expense;
import tech.drufontael.carshop.model.Image;
import tech.drufontael.carshop.model.Vehicle;
import tech.drufontael.carshop.services.VehicleService;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/vehicles")
@CrossOrigin(origins = "*")
public class VehicleController implements VehicleDoc {
    @Autowired
    VehicleService service;

    @PostMapping
    public ResponseEntity<VehicleDto> create(@RequestBody VehicleDto vehicle){
        vehicle=service.create(vehicle);
        vehicle.add(linkTo(methodOn(VehicleController.class).findById(vehicle.getPlate())).withSelfRel());
        vehicle.add(linkTo(methodOn(VehicleController.class).findall()).withRel("List of vehicles"));
        return ResponseEntity.status(HttpStatus.CREATED).body(vehicle);
    }

    @GetMapping
    public ResponseEntity<List<VehicleDto>> findall(){
        List<VehicleDto> vehicles=service.findAll();
        for(VehicleDto vehicle:vehicles){
            vehicle.add(linkTo(methodOn(ExpenseController.class).findExpensesByPlate(vehicle.getPlate()))
                    .withRel("Expenses"));
            vehicle.add(linkTo(VehicleController.class).slash(vehicle.getPlate()).withSelfRel());
        }
        return ResponseEntity.status(HttpStatus.OK).body(vehicles);
    }

    @GetMapping("/{plate}")
    public ResponseEntity<VehicleDto> findById(@PathVariable String plate){
        VehicleDto vehicle=service.read(plate);
        vehicle.add(linkTo(methodOn(ExpenseController.class).findExpensesByPlate(vehicle.getPlate()))
                .withRel("Expenses"));
        vehicle.add(linkTo(methodOn(VehicleController.class).findall()).withRel("Vehicle list"));
        return ResponseEntity.status(HttpStatus.OK).body(vehicle);
    }

    @PutMapping("/{plate}")
    public ResponseEntity<VehicleDto> update(@PathVariable String plate,@RequestBody VehicleDto dto){
        VehicleDto vehicle=service.update(plate,dto);
        vehicle.add(linkTo(methodOn(VehicleController.class).findById(vehicle.getPlate())).withSelfRel());
        vehicle.add(linkTo(methodOn(ExpenseController.class).findExpensesByPlate(vehicle.getPlate()))
                .withRel("Expenses"));
        vehicle.add(linkTo(methodOn(VehicleController.class).findall()).withRel("List of vehicles"));

        return ResponseEntity.status(HttpStatus.OK).body(vehicle);
    }

    @DeleteMapping("/{plate}")
    public ResponseEntity delete(@PathVariable String plate){
        service.delete(plate);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @PostMapping("{plate}/images")
    public ResponseEntity addImage(@PathVariable String plate,@RequestBody ImageDto dto){
        service.addImage(plate,dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{plate}/images")
    public ResponseEntity<List<ImageDto>> findImages(@PathVariable String plate){
        return ResponseEntity.ok(service.findImages(plate));
    }


}
