package tech.drufontael.carshop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tb_consigment")
public class Consignment {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    @ManyToOne
    @JoinColumn(name ="register",referencedColumnName = "register")
    private Consignor consignor;
    @ManyToOne
    @JoinColumn(name ="plate",referencedColumnName = "plate")
    private Vehicle vehicle;
    private String location;
    private LocalDate date;
    private LocalTime entryTime;
    private boolean opened;
}
