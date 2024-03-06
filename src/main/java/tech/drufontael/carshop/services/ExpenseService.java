package tech.drufontael.carshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.drufontael.carshop.dto.ExpenseDto;
import tech.drufontael.carshop.exceptions.ResourseNotFoundException;
import tech.drufontael.carshop.model.Expense;

import tech.drufontael.carshop.repositories.ExpenseRepository;
import tech.drufontael.carshop.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ExpenseService {
    @Autowired
    private ExpenseRepository repository;
    @Autowired
    private VehicleService vehicleService;
    
    
    @Transactional
    public ExpenseDto create(ExpenseDto dto){
        var vehicle=vehicleService.read(dto.getPlate()).toVehicle();
        Expense expense =dto.toExpense();
        expense.setVehicle(vehicle);
        expense =repository.save(expense);

        return new ExpenseDto(expense);
    }

    public ExpenseDto read(UUID id){
        return new ExpenseDto(repository.findById(id)
                .orElseThrow(()->new ResourseNotFoundException("Spent not found")));
    }

    public Expense update(UUID id, Object source){
        Expense target=repository.findById(id).orElseThrow(()->new ResourseNotFoundException("Spent not found."));
        Utils.copyNonNullProperties(source,target);
        return repository.save(target);
    }

    public void delete(UUID id){
        Expense expense =repository.findById(id).orElseThrow(()->new ResourseNotFoundException("Spent not found"));
        repository.delete(expense);
    }

    public List<ExpenseDto> findAll(){
        List<ExpenseDto> dtos=new ArrayList<>();
        for(Expense expense:repository.findAll()){
            dtos.add(new ExpenseDto(expense));
        }
        return dtos;
    }

    public List<ExpenseDto> findAllByVehiclePlate(String plate){
        List<ExpenseDto> dtos=new ArrayList<>();
        for(Expense expense:repository.findByVehiclePlate(plate)){
            dtos.add(new ExpenseDto(expense));
        }
        return dtos;
    }


}
