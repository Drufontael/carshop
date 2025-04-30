package tech.drufontael.carshop.modules.shared.utils;

import br.com.caelum.stella.inwords.FormatoDeReal;
import br.com.caelum.stella.inwords.NumericToWordsConverter;

public class PorExtenso {
    public static String converter(Integer numero){
        NumericToWordsConverter converter=new NumericToWordsConverter(new FormatoDeReal());
        return converter.toWords(numero);
    }
}
