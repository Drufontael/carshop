package tech.drufontael.carshop.modules.veiculos.domain;

import jakarta.persistence.*;
import lombok.*;
import tech.drufontael.carshop.modules.veiculos.domain.valor_objeto.AnoFabricacao;
import tech.drufontael.carshop.modules.veiculos.domain.valor_objeto.Chassi;
import tech.drufontael.carshop.modules.veiculos.domain.valor_objeto.Placa;
import tech.drufontael.carshop.modules.veiculos.domain.valor_objeto.Renavan;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Embeddable
public class DadosVeiculo {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modelo_id")
    protected Modelo modelo;
    @Column(length = 7)
    protected Placa placa;
    @Column(length = 17)
    protected Chassi chassi;
    @Column(length = 11)
    protected Renavan renavan;
    protected AnoFabricacao anoFabricacao;

    private void validaAno(AnoFabricacao anoFabricacao,Modelo modelo){
        if(anoFabricacao.getValue()<modelo.anoModelo.getValue()-1){
            throw new  RuntimeException("Ano de fabricação incoerente: "+this.anoFabricacao);
        }
        if(anoFabricacao.getValue()>modelo.anoModelo.getValue()){
            throw new  RuntimeException("Ano de fabricação incoerente: "+this.anoFabricacao);
        }
    }

    public void setAnoFabricacao(AnoFabricacao anoFabricacao){
        if(this.modelo!=null) validaAno(anoFabricacao,this.modelo);
        this.anoFabricacao=anoFabricacao;
    }

    public void setModelo(Modelo modelo){
        if(this.anoFabricacao!=null) validaAno(this.anoFabricacao,modelo);
        this.modelo=modelo;
    }


}
