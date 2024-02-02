package tech.drufontael.carshop.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity(name = "tb_vehicle")
public class Vehicle{
    private String brand;
    private String modelCar;
    private Integer year;
    private Integer modelYear;
    private Integer km;
    @Id
    private String plate;
    private String chassis;
    private String renavan;
    private String ownerName;
    private String ownerIdentity;
    private Integer price;
    private Boolean manual;
    private Boolean extraKey;
    private Boolean tools;
    private Boolean dut;
    private Integer yearDocument;
    private Double totalExpenses;
    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "vehicle_plate")
    private List<Expense> expenses =new ArrayList<>();
    @OneToMany(cascade = CascadeType.REMOVE)
    //@JoinColumn(name = "vehicle_plate")
    private List<Image> images=new ArrayList<>();

    public Double getTotalExpenses(){
        if(expenses.size()==0) return 0.0;
        return expenses.stream().mapToDouble(Expense::getValor).sum();
    }
}
