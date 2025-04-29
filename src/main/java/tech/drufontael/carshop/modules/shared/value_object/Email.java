package tech.drufontael.carshop.modules.shared.value_object;

import java.util.regex.Pattern;

public class Email {
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
                    "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$"
    );
    private String email;

    public Email(String email){
        validarEmail(email);
        this.email=email;
    }

    private static void validarEmail(String email) {
        if (email == null) {
            throw new IllegalArgumentException("E-mail inválido: não pode ser null");
        }
        if (email.isEmpty()) {
            throw new IllegalArgumentException("E-mail inválido: não pode ser vazio");
        }
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new IllegalArgumentException("E-mail inválido: " + email + " (formato incorreto)");
        }
        if (email.length() > 254) {
            throw new IllegalArgumentException("E-mail inválido: " + email + " (muito longo)");
        }
    }

    public String getValue(){
        return this.email;
    }
}
