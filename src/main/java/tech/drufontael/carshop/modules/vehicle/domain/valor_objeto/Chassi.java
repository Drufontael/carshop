package tech.drufontael.carshop.modules.vehicle.domain.valor_objeto;

import tech.drufontael.carshop.exceptions.InvalidArgumentFormatException;

import java.util.HashMap;
import java.util.Map;

public class Chassi {
    private static final Map<Character, Integer> VALORES_LETRAS;
    private static final Map<Integer, Integer> PESOS_POSICAO;
    private static final int TAMANHO_CHASSI = 17;
    private static final int MODULO = 11;
    protected final String chassi;

    static {
        VALORES_LETRAS = new HashMap<>();
        VALORES_LETRAS.put('A', 1);
        VALORES_LETRAS.put('B', 2);
        VALORES_LETRAS.put('C', 3);
        VALORES_LETRAS.put('D', 4);
        VALORES_LETRAS.put('E', 5);
        VALORES_LETRAS.put('F', 6);
        VALORES_LETRAS.put('G', 7);
        VALORES_LETRAS.put('H', 8);
        VALORES_LETRAS.put('J', 1);
        VALORES_LETRAS.put('K', 2);
        VALORES_LETRAS.put('L', 3);
        VALORES_LETRAS.put('M', 4);
        VALORES_LETRAS.put('N', 5);
        VALORES_LETRAS.put('P', 7);
        VALORES_LETRAS.put('R', 9);
        VALORES_LETRAS.put('S', 2);
        VALORES_LETRAS.put('T', 3);
        VALORES_LETRAS.put('U', 4);
        VALORES_LETRAS.put('V', 5);
        VALORES_LETRAS.put('W', 6);
        VALORES_LETRAS.put('X', 7);
        VALORES_LETRAS.put('Y', 8);
        VALORES_LETRAS.put('Z', 9);
        PESOS_POSICAO = new HashMap<>();
        PESOS_POSICAO.put(1, 8);
        PESOS_POSICAO.put(2, 7);
        PESOS_POSICAO.put(3, 6);
        PESOS_POSICAO.put(4, 5);
        PESOS_POSICAO.put(5, 4);
        PESOS_POSICAO.put(6, 3);
        PESOS_POSICAO.put(7, 2);
        PESOS_POSICAO.put(8, 10);
        PESOS_POSICAO.put(9, 0);
        PESOS_POSICAO.put(10, 9);
        PESOS_POSICAO.put(11, 8);
        PESOS_POSICAO.put(12, 7);
        PESOS_POSICAO.put(13, 6);
        PESOS_POSICAO.put(14, 5);
        PESOS_POSICAO.put(15, 4);
        PESOS_POSICAO.put(16, 3);
        PESOS_POSICAO.put(17, 2);
    }

    public Chassi(String chassi){
        validarChassi(chassi);
        this.chassi=chassi;
    }
    private static void validarChassi(String chassi) {
        if (chassi == null || chassi.length() != TAMANHO_CHASSI) {
            throw new InvalidArgumentFormatException("Tamanho do chassi invalido: "+chassi.length());
        }

        chassi = chassi.toUpperCase();
        int soma = 0;

        for (int i = 0; i < chassi.length(); i++) {
            char c = chassi.charAt(i);
            int valor;
            int posicao = i + 1;


            valor=Character.isDigit(c)?Character.getNumericValue(c):10;
            valor= VALORES_LETRAS.getOrDefault(c, valor);
            if (valor==10) {
                throw new InvalidArgumentFormatException("Chassi inconsistente: "+chassi);
            }


            soma += valor * PESOS_POSICAO.get(posicao);
        }
        int resto = soma % MODULO;

        char digitoVerificador = chassi.charAt(8);

        // Verifica o dígito verificador
        boolean condicao1=(resto==10)&&(digitoVerificador=='X');
        boolean condicao2= Character.isDigit(digitoVerificador) && Character.getNumericValue(digitoVerificador) == resto;
        if(!condicao1 && !condicao2) throw new InvalidArgumentFormatException("Chassi inconsistente: "+chassi);

    }

    public String getValue(){
        return this.chassi;
    }
}
