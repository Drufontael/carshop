package tech.drufontael.carshop.modules.consignment.domain;

import jakarta.persistence.*;
import lombok.Data;
import tech.drufontael.carshop.modules.customer.domain.Customer;
import tech.drufontael.carshop.modules.shared.Address;
import tech.drufontael.carshop.modules.vehicle.domain.Vehicle;

import java.time.LocalDateTime;

@Entity
@Data
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

    private LocalDateTime dateTime;
}

