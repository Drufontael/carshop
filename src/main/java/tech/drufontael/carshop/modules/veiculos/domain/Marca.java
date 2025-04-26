package tech.drufontael.carshop.modules.veiculos.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Marca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)  // Garante que o nome seja único
    private String marca;

    public Marca(String marca){
        this.marca=marca;
    }
}
