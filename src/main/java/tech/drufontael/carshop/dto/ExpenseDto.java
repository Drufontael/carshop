package tech.drufontael.carshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.RepresentationModel;
import tech.drufontael.carshop.model.Expense;
import tech.drufontael.carshop.model.Vehicle;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseDto extends RepresentationModel<ExpenseDto> {
    private UUID id;
    private String plate;
    private String description;
    private LocalDate date;
    private Double valor;

    public ExpenseDto(Expense expense){
        BeanUtils.copyProperties(expense,this);
        setPlate(expense.getVehicle().getPlate());
    }


    public Expense toExpense(){
        return new Expense(null,new Vehicle(),getDescription(),getDate(),getValor());
    }

}
