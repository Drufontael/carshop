package tech.drufontael.carshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.drufontael.carshop.documentation.ExpenseDoc;
import tech.drufontael.carshop.dto.ExpenseDto;
import tech.drufontael.carshop.services.ExpenseService;

import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/expenses")
@CrossOrigin(origins = "*")
public class ExpenseController implements ExpenseDoc {
    @Autowired
    ExpenseService service;

    @PostMapping
    public ResponseEntity<ExpenseDto> create(@RequestBody ExpenseDto obj){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(obj));
    }

    @GetMapping
    public ResponseEntity<List<ExpenseDto>> findall(){
        List<ExpenseDto> expenses =service.findAll();
        for(ExpenseDto expense : expenses){
            Link link= linkTo(ExpenseController.class).slash(expense.getId()).withSelfRel();
            expense.add(link);
            expense.add(linkTo(methodOn(VehicleController.class).findById(expense.getPlate()))
                    .withRel("Vehicle"));
        }
        Link link=linkTo(ExpenseController.class).withSelfRel();
        return ResponseEntity.status(HttpStatus.OK).body(expenses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseDto> findById(@PathVariable UUID id){
        ExpenseDto expense =service.read(id);
        expense.add(linkTo(methodOn(ExpenseController.class).findall()).withRel("Expenses list"));
        expense.add(linkTo(methodOn(VehicleController.class).findById(expense.getPlate()))
                .withRel("Vehicle"));
        return ResponseEntity.status(HttpStatus.OK).body(expense);
    }


    @GetMapping("/{plate}/list")
    public ResponseEntity<List<ExpenseDto>> findExpensesByPlate(@PathVariable String plate){
        List<ExpenseDto> expenses=service.findAllByVehiclePlate(plate);
        for (ExpenseDto expense:expenses){
            expense.add(linkTo(methodOn(ExpenseController.class).findById(expense.getId())).withSelfRel());
            expense.add(linkTo(methodOn(VehicleController.class).findById(plate)).withRel("Vehicle"));
        }
        return ResponseEntity.ok(expenses);
    }
}
