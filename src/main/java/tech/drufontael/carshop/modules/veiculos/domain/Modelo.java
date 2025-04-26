package tech.drufontael.carshop.modules.veiculos.domain;

import jakarta.persistence.*;
import lombok.*;
import tech.drufontael.carshop.modules.veiculos.domain.valor_objeto.AnoModelo;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Modelo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String modelo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "marca_id", nullable = false)
    protected Marca marca;
    protected AnoModelo anoModelo;
}
