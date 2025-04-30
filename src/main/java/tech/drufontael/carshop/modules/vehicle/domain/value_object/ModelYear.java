package tech.drufontael.carshop.modules.vehicle.domain.value_object;

import tech.drufontael.carshop.exceptions.InvalidArgumentFormatException;

import java.time.LocalDate;

public class ModelYear {

    protected final int anoModelo;

    public ModelYear(int anoModelo){
        int anoAtual= LocalDate.now().getYear();
        if(anoModelo<1950 || anoModelo>anoAtual) throw new InvalidArgumentFormatException("Ano "+anoModelo+" incoerente!");
        this.anoModelo=anoModelo;
    }

    public int getValue(){
        return this.anoModelo;
    }
}


