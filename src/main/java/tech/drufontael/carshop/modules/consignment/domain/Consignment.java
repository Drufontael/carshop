package tech.drufontael.carshop.modules.consignment.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.drufontael.carshop.modules.customer.domain.Customer;
import tech.drufontael.carshop.modules.shared.Address;
import tech.drufontael.carshop.modules.shared.CarshopConstants;
import tech.drufontael.carshop.modules.vehicle.domain.Vehicle;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Consignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "consignor_id", nullable = false)
    private Customer consignor;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    @Embedded
    private Address address;
    @Column( precision = 15, scale = 2)
    private BigDecimal minimumPrice;
    private Double commission;
    private LocalDateTime dateTime;

    public  Consignment(Customer customer,Vehicle vehicle,Double commission,BigDecimal minimumPrice){
        this.consignor=customer;
        this.vehicle=vehicle;
        this.commission=commission;
        this.minimumPrice=minimumPrice;
        this.address= CarshopConstants.SHOP_ADDRESS;
        this.dateTime=LocalDateTime.now();
    }
}

