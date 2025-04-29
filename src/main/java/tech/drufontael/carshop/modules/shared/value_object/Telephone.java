package tech.drufontael.carshop.modules.shared.value_object;

import java.util.regex.Pattern;

public class Telephone {
    private static final Pattern TELEPHONE_PATTERN = Pattern.compile(
            "^(\\+?55)?\\s?(\\(?\\d{2}\\)?)?\\s?(9?\\d{4}[-\\s]?\\d{4})$"
    );
    private final String telephone;

    public Telephone(String telephone){
        this.telephone=telephoneFormatter(telephone);
    }

    private static void telephoneValidate(String telephone) throws IllegalArgumentException {
        if (telephone == null) {
            throw new IllegalArgumentException("Telephone inválido: não pode ser null");
        }
        String numeroLimpo = telephone.replaceAll("[\\s()-]", "");
        if (numeroLimpo.length() < 10 || numeroLimpo.length() > 13) {
            throw new IllegalArgumentException(
                    String.format("Telephone inválido: %s (tamanho incorreto)", telephone)
            );
        }
        if (!numeroLimpo.matches("^(\\+?55)?\\d{10,11}$")) {
            throw new IllegalArgumentException(
                    String.format("Telephone inválido: %s (formato incorreto)", telephone)
            );
        }
        String numeroSemDDD = numeroLimpo.replaceAll("^\\+?55?", "");
        if (numeroSemDDD.length() == 11 && !numeroSemDDD.startsWith("9", 2)) {
            throw new IllegalArgumentException(
                    String.format("Telephone inválido: %s (celular deve começar com 9)", telephone)
            );
        }
    }
    private static String telephoneFormatter(String telephone) {
        telephoneValidate(telephone); // Primeiro valida

        String numeroLimpo = telephone.replaceAll("[^0-9+]", "");

        // Se tiver código do país, mantém
        boolean temCodigoPais = numeroLimpo.startsWith("55") || numeroLimpo.startsWith("+55");
        String numeroSemCodigo = temCodigoPais ? numeroLimpo.substring(numeroLimpo.length() - 11) : numeroLimpo;

        // Formata (XX) XXXX-XXXX ou (XX) 9XXXX-XXXX
        String ddd = numeroSemCodigo.substring(0, 2);
        String parte1 = numeroSemCodigo.substring(2, numeroSemCodigo.length() - 4);
        String parte2 = numeroSemCodigo.substring(numeroSemCodigo.length() - 4);

        return temCodigoPais
                ? String.format("+55 (%s) %s-%s", ddd, parte1, parte2)
                : String.format("(%s) %s-%s", ddd, parte1, parte2);
    }

    public String getValue(){
        return this.telephone;
    }
}
