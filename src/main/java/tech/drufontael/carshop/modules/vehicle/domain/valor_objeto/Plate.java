package tech.drufontael.carshop.modules.vehicle.domain.valor_objeto;

import tech.drufontael.carshop.exceptions.InvalidArgumentFormatException;

import java.util.regex.Pattern;

public class Plate {
    protected final String placa;
    private final Pattern PLACA_ANTIGA = Pattern.compile("[A-Za-z]{3}[0-9]{4}");
    private final Pattern PLACA_MERCOSUL = Pattern.compile("[A-Za-z]{3}[0-9][A-Za-z][0-9]{2}");

    public Plate(String placa){
        validarPlaca(placa);
        this.placa=placaFormat(placa);
    }

    private void validarPlaca(String placa){
        if (placa == null) {
            throw new InvalidArgumentFormatException("Placa inválida: null");
        }
        String placaLimpa = placaFormat(placa);
        if (placaLimpa.length() != 7) {
            throw new InvalidArgumentFormatException("Placa inválida: " + placa + " (deve ter 7 caracteres)");
        }
        boolean valida = PLACA_ANTIGA.matcher(placaLimpa).matches() ||
                PLACA_MERCOSUL.matcher(placaLimpa).matches();
        if (!valida) {
            throw new InvalidArgumentFormatException("Placa inválida: " + placa + " (formato não reconhecido)");
        }
    }

    private String placaFormat(String placa){
        return placa.replaceAll("[-\\s]", "").toUpperCase();
    }


    public String getValue(){
        return this.placa;
    }
}
