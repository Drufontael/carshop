package tech.drufontael.carshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.drufontael.carshop.model.Expense;
import tech.drufontael.carshop.model.Vehicle;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseDto {
    private UUID id;
    private String plate;
    private String description;
    private LocalDate date;
    private Double valor;


    public Expense toExpense(){
        return new Expense(null,new Vehicle(),getDescription(),getDate(),getValor());
    }

}
