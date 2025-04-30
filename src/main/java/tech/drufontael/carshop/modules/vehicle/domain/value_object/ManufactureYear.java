package tech.drufontael.carshop.modules.vehicle.domain.value_object;

import tech.drufontael.carshop.exceptions.InvalidArgumentFormatException;

import java.time.LocalDate;

public class ManufactureYear {
    protected final int anoFabricacao;

    public ManufactureYear(int anoFabricacao){
        int anoAtual= LocalDate.now().getYear();
        if(anoFabricacao<1950 || anoFabricacao>anoAtual) throw new InvalidArgumentFormatException("Ano "+anoFabricacao+" incoerente!");
        this.anoFabricacao=anoFabricacao;
    }

    public int getValue(){
        return this.anoFabricacao;
    }
}
