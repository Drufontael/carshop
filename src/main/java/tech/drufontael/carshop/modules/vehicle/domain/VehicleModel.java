package tech.drufontael.carshop.modules.vehicle.domain;

import jakarta.persistence.*;
import lombok.*;
import tech.drufontael.carshop.modules.vehicle.domain.valor_objeto.ModelYear;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class VehicleModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String modelo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id", nullable = false)
    protected Brand brand;
    protected ModelYear modelYear;
}
