package tech.drufontael.carshop.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    @ElementCollection
    @CollectionTable(name = "tb-images",joinColumns = @JoinColumn(name = "vehiclePlate"))
    @MapKeyColumn(name = "description")
    @Column(name = "path")
    private Map<String,String> images;
    private Boolean active;

    public Double getTotalExpenses(){
        if(expenses.isEmpty()) return 0.0;
        return expenses.stream().mapToDouble(Expense::getValor).sum();
    }

    @Transient
    private String formattedPrice;

    @Transient
    private String priceInWords;
}
