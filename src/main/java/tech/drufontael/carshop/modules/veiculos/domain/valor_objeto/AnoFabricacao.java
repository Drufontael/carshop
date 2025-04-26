package tech.drufontael.carshop.modules.veiculos.domain.valor_objeto;

import java.time.LocalDate;

public class AnoFabricacao {
    protected final int anoFabricacao;

    public AnoFabricacao(int anoFabricacao){
        int anoAtual= LocalDate.now().getYear();
        if(anoFabricacao<1950 || anoFabricacao>anoAtual) throw new RuntimeException("Ano "+anoFabricacao+" incoerente!");
        this.anoFabricacao=anoFabricacao;
    }

    public int getValue(){
        return this.anoFabricacao;
    }
}
