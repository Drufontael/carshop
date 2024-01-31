package tech.drufontael.carshop.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.drufontael.carshop.documentation.ExpenseDoc;
import tech.drufontael.carshop.dto.ExpenseDto;
import tech.drufontael.carshop.model.Expense;
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

    @PostMapping("/")
    public ResponseEntity<ExpenseDto> create(@RequestBody ExpenseDto obj){
        Expense expense =service.create(obj);
        obj.setId(expense.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(obj);
    }

    @GetMapping("/")
    public ResponseEntity<List<Expense>> findall(){
        List<Expense> expenses =service.findAll();
        for(Expense expense : expenses){
            Link link= linkTo(ExpenseController.class).slash(expense.getId()).withSelfRel();
            expense.add(link);
            expense.add(linkTo(methodOn(VehicleController.class).findById(expense.getVehicle().getPlate()))
                    .withRel("Vehicle"));
        }
        Link link=linkTo(ExpenseController.class).withSelfRel();
        return ResponseEntity.status(HttpStatus.OK).body(expenses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Expense> findById(@PathVariable UUID id){
        Expense expense =service.read(id);
        expense.add(linkTo(methodOn(ExpenseController.class).findall()).withRel("Expenses list"));
        expense.add(linkTo(methodOn(VehicleController.class).findById(expense.getVehicle().getPlate()))
                .withRel("Vehicle"));
        return ResponseEntity.status(HttpStatus.OK).body(expense);
    }


    @GetMapping("/{plate}/list")
    public ResponseEntity<List<Expense>> findExpensesByPlate(@PathVariable String plate){
        List<Expense> expenses=service.findAllByVehiclePlate(plate);
        for (Expense expense:expenses){
            expense.add(linkTo(methodOn(ExpenseController.class).findById(expense.getId())).withSelfRel());
        }
        return ResponseEntity.ok(expenses);
    }
}
