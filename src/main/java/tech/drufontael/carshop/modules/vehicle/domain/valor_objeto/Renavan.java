package tech.drufontael.carshop.modules.vehicle.domain.valor_objeto;

import tech.drufontael.carshop.exceptions.InvalidArgumentFormatException;

public class Renavan {

    protected final String renavan;

    public Renavan(String renavan){
        validarRenavam(renavan);
        this.renavan=renavan;
    }

    private void validarRenavam(String renavam) {

        renavam = renavam.replaceAll("[^0-9]", "");

        if (renavam.length() != 9 && renavam.length() != 11) {
            throw new InvalidArgumentFormatException("Renavan invalido: "+renavam);
        }
        if (renavam.length() == 9) {
            renavam = "00" + renavam;
        }
        int digitoVerificador = Character.getNumericValue(renavam.charAt(10));
        int soma = 0;
        int[] pesos = {3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        for (int i = 0; i < 10; i++) {
            int digito = Character.getNumericValue(renavam.charAt(i));
            soma += digito * pesos[i];
        }
        int resto = soma % 11;
        int digitoCalculado = (resto == 0 || resto == 1) ? 0 : (11 - resto);

        if( digitoVerificador != digitoCalculado) throw new InvalidArgumentFormatException("Renavan invalido: "+renavam);
    }

    public String getvalue(){
        return this.renavan;
    }
}
