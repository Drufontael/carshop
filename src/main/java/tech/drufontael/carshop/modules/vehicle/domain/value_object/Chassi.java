package tech.drufontael.carshop.modules.vehicle.domain.value_object;

import tech.drufontael.carshop.exceptions.InvalidArgumentFormatException;

import java.util.HashMap;
import java.util.Map;

public class Chassi {
    private static final Map<Character, Integer> VALORES_LETRAS;
    private static final Map<Integer, Integer> PESOS_POSICAO;
    private static final int TAMANHO_CHASSI = 17;
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
        for (int i = 1; i <= TAMANHO_CHASSI; i++) {
            PESOS_POSICAO.put(i, 1); // Mantido apenas por legado, não usado agora
        }
    }

    public Chassi(String chassi) {
        validarChassi(chassi);
        this.chassi = chassi.toUpperCase();
    }

    private static void validarChassi(String chassi) {
        if (chassi == null || chassi.length() != TAMANHO_CHASSI) {
            throw new InvalidArgumentFormatException("Tamanho do chassi inválido: " + (chassi == null ? "null" : chassi.length()));
        }

        chassi = chassi.toUpperCase();

        for (int i = 0; i < chassi.length(); i++) {
            char c = chassi.charAt(i);

            boolean valido = Character.isDigit(c) || VALORES_LETRAS.containsKey(c);
            if (!valido) {
                throw new InvalidArgumentFormatException("Caractere inválido no chassi: '" + c + "' na posição " + (i + 1));
            }
        }
    }



    public String getValue(){
        return this.chassi;
    }
}
