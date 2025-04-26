package tech.drufontael.carshop.modules.veiculos.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    protected DadosVeiculo dadosVeiculo;
    @Column(nullable = false, precision = 15, scale = 2)
    protected BigDecimal preco;

    public void setPreco(BigDecimal preco){
        if(preco.compareTo(BigDecimal.ZERO)<0) throw new RuntimeException("Preço não pode ser negativo"+preco.toString());
        this.preco=preco;
    }
}
