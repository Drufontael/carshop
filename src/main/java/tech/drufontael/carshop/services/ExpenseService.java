package tech.drufontael.carshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.drufontael.carshop.dto.ExpenseDto;
import tech.drufontael.carshop.exceptions.ResourseNotFoundException;
import tech.drufontael.carshop.model.Expense;

import tech.drufontael.carshop.model.Vehicle;
import tech.drufontael.carshop.repositories.ExpenseRepository;
import tech.drufontael.carshop.utils.Utils;

import java.util.List;
import java.util.UUID;

@Service
public class ExpenseService {
    @Autowired
    private ExpenseRepository repository;
    @Autowired
    private VehicleService vehicleService;
    
    
    @Transactional
    public Expense create(ExpenseDto dto){
        Vehicle vehicle=vehicleService.read(dto.getPlate());
        Expense expense =dto.toExpense();
        expense.setVehicle(vehicle);
        expense =repository.save(expense);
        vehicle.getExpenses().add(expense);
        vehicleService.update(vehicle.getPlate(),vehicle);
        return expense;
    }

    public Expense read(UUID id){
        return repository.findById(id).orElseThrow(()->new ResourseNotFoundException("Spent not found"));
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

    public List<Expense> findAll(){
        return repository.findAll();
    }

    public List<Expense> findAllByVehiclePlate(String plate){
        List<Expense> expenses=repository.findByVehiclePlate(plate);
        return expenses;
    }


}
