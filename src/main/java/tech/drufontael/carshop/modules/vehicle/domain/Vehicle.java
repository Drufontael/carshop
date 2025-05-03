package tech.drufontael.carshop.modules.vehicle.domain;

import jakarta.persistence.*;
import lombok.Data;
import tech.drufontael.carshop.exceptions.InvalidArgumentFormatException;
import tech.drufontael.carshop.modules.customer.domain.Customer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    protected VehicleData vehicleData;

    @Column(nullable = false, precision = 15, scale = 2)
    protected BigDecimal price;
    protected Integer mileage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    protected Customer owner;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "vehicle_additional_info", joinColumns = @JoinColumn(name = "vehicle_id"))
    @Column(name = "information")
    protected List<String> additionalInformations = new ArrayList<>();

    public void setPrice(BigDecimal price) {
        if (price.compareTo(BigDecimal.ZERO) < 0)
            throw new RuntimeException("Preço não pode ser negativo: " + price.toString());
        this.price = price;
    }

    public void setMileage(Integer mileage){
        if(mileage<0) throw new InvalidArgumentFormatException("Kilometragem não pode ser negativa: "+mileage);
        this.mileage=mileage;
    }
}
