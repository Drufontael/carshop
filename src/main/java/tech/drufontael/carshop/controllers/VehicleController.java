package tech.drufontael.carshop.controllers;


import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
public class VehicleController {
    @Autowired
    VehicleService service;

    @PostMapping("/")
    @Operation(summary = "Create",description = "Cadastra um veiculo")
    public ResponseEntity<Vehicle> create(@RequestBody VehicleDto obj){
        Vehicle vehicle=obj.toVehicle();
        vehicle=service.create(vehicle);
        vehicle.add(linkTo(methodOn(VehicleController.class).findById(vehicle.getPlate())).withSelfRel());
        vehicle.add(linkTo(methodOn(VehicleController.class).findall()).withRel("List of vehicles"));
        return ResponseEntity.status(HttpStatus.CREATED).body(vehicle);
    }

    @GetMapping("/")
    @Operation(summary = "List All",description = "Lista todos os veiculos cadastrados")
    public ResponseEntity<List<Vehicle>> findall(){
        List<Vehicle> vehicles=service.findAll();
        for(Vehicle vehicle:vehicles){
            for(Expense expense :vehicle.getExpenses()){
                expense.add(linkTo(methodOn(ExpenseController.class).findById(expense.getId())).withSelfRel());
            }
            Link link= linkTo(VehicleController.class).slash(vehicle.getPlate()).withSelfRel();
            vehicle.add(link);
        }
        return ResponseEntity.status(HttpStatus.OK).body(vehicles);
    }

    @GetMapping("/{plate}")
    @Operation(summary = "Read",description = "Retorna um veículo pela placa")
    public ResponseEntity<Vehicle> findById(@PathVariable String plate){
        Vehicle vehicle=service.read(plate);
        for(Expense expense :vehicle.getExpenses()){
            expense.add(linkTo(methodOn(ExpenseController.class).findById(expense.getId())).withSelfRel());
        }
        vehicle.add(linkTo(methodOn(VehicleController.class).findall()).withRel("Vehicle list"));
        return ResponseEntity.status(HttpStatus.OK).body(vehicle);
    }

    @PutMapping("/{plate}")
    @Operation(summary = "Update",description = "Atualiza um veiculo")
    public ResponseEntity<Vehicle> update(@PathVariable String plate,@RequestBody VehicleDto dto){
        Vehicle vehicle=service.update(plate,dto);
        vehicle.add(linkTo(methodOn(VehicleController.class).findById(vehicle.getPlate())).withSelfRel());
        vehicle.add(linkTo(methodOn(VehicleController.class).findall()).withRel("List of vehicles"));

        return ResponseEntity.status(HttpStatus.OK).body(vehicle);
    }

    @DeleteMapping("/{plate}")
    @Operation(summary = "Delete",description = "Exclui um veiculo pela placa")
    public ResponseEntity delete(@PathVariable String plate){
        service.delete(plate);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @PostMapping("{plate}/images")
    @Operation(summary = "Add image", description = "Adiciona uma imagem a um veiculo")
    public ResponseEntity addImage(@PathVariable String plate,@RequestBody ImageDto dto){
        service.addImage(plate,dto.toImage());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{plate}/images")
    @Operation(summary = "Image List",description = "Lista todas imagens de um veiculo")
    public ResponseEntity<List<Image>> findImages(@PathVariable String plate){
        return ResponseEntity.ok(service.findImages(plate));
    }


}
