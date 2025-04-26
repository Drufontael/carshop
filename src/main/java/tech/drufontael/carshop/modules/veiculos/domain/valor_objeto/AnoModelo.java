package tech.drufontael.carshop.modules.veiculos.domain.valor_objeto;

import java.time.LocalDate;

public class AnoModelo {

    protected final int anoModelo;

    public AnoModelo(int anoModelo){
        int anoAtual= LocalDate.now().getYear();
        if(anoModelo<1950 || anoModelo>anoAtual) throw new RuntimeException("Ano "+anoModelo+" incoerente!");
        this.anoModelo=anoModelo;
    }

    public int getValue(){
        return this.anoModelo;
    }
}


