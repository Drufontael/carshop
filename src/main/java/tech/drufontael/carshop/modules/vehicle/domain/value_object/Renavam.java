package tech.drufontael.carshop.modules.vehicle.domain.value_object;

import tech.drufontael.carshop.exceptions.InvalidArgumentFormatException;

public class Renavam {

    private final String renavam;

    public Renavam(String renavam){
        this.renavam = validarRenavam(renavam);
    }

    private String validarRenavam(String renavam) {
        renavam = renavam.replaceAll("[^0-9]", "");

        if (renavam.length() != 9 && renavam.length() != 11) {
            throw new InvalidArgumentFormatException("Renavam inválido: " + renavam);
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

        if (digitoVerificador != digitoCalculado) {
            throw new InvalidArgumentFormatException("Renavam inválido: " + renavam);
        }

        return renavam; // retorna renavam validado e com 11 dígitos
    }

    public String getValue() {
        return this.renavam;
    }
}
