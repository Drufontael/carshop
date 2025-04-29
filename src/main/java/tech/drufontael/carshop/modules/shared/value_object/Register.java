package tech.drufontael.carshop.modules.shared.value_object;

import lombok.Getter;
import tech.drufontael.carshop.exceptions.InvalidArgumentFormatException;

public class Register {
    private final String register;
    @Getter
    private final RegisterType registerType;



    public Register(String register){
        String numeros=register.replaceAll("[^0-9]","");
        this.registerType = setRegisterType(numeros);
        this.register =this.registerType.equals(RegisterType.CPF)?cpfFormat(numeros):cnpjFormat(numeros);
    }

    private boolean isCPF(String cpf){
        if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        try {
            // Calcula o primeiro dígito verificador
            int soma = 0;
            for (int i = 0; i < 9; i++) {
                soma += (cpf.charAt(i) - '0') * (10 - i);
            }
            int digito1 = 11 - (soma % 11);
            if (digito1 > 9) digito1 = 0;

            // Calcula o segundo dígito verificador
            soma = 0;
            for (int i = 0; i < 10; i++) {
                soma += (cpf.charAt(i) - '0') * (11 - i);
            }
            int digito2 = 11 - (soma % 11);
            if (digito2 > 9) digito2 = 0;
            return (cpf.charAt(9) - '0' == digito1) &&
                    (cpf.charAt(10) - '0' == digito2);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isCNPJ(String cnpj){
        if (cnpj.length() != 14 || cnpj.matches("(\\d)\\1{13}")) {
            return false;
        }

        try {
            int soma = 0;
            int peso = 2;
            for (int i = 11; i >= 0; i--) {
                soma += (cnpj.charAt(i) - '0') * peso;
                peso = (peso == 9) ? 2 : peso + 1;
            }
            int digito1 = 11 - (soma % 11);
            if (digito1 > 9) digito1 = 0;

            // Cálculo do segundo dígito verificador
            soma = 0;
            peso = 2;
            for (int i = 12; i >= 0; i--) {
                soma += (cnpj.charAt(i) - '0') * peso;
                peso = (peso == 9) ? 2 : peso + 1;
            }
            int digito2 = 11 - (soma % 11);
            if (digito2 > 9) digito2 = 0;

            return (cnpj.charAt(12) - '0' == digito1) &&
                    (cnpj.charAt(13) - '0' == digito2);
        } catch (NumberFormatException e) {
            return false;
        }

    }
    private RegisterType setRegisterType(String numeros){
        if(isCPF(numeros)) return RegisterType.CPF;
        if (isCNPJ(numeros)) return RegisterType.CNPJ;
        throw new InvalidArgumentFormatException("Valor do registro invalido: "+numeros);
    }

    private String cpfFormat(String cpf){
        return cpf.substring(0, 3) +
                "." +
                cpf.substring(3, 6) +
                "." +
                cpf.substring(6, 9) +
                "-" +
                cpf.substring(9);
    }
    private String cnpjFormat(String cnpj){
        return cnpj.substring(0, 2) +
                "." +
                cnpj.substring(2, 5) +
                "." +
                cnpj.substring(5, 8) +
                "/" +
                cnpj.substring(8, 12) +
                "-" +
                cnpj.substring(12);
    }

    @Override
    public String toString() {
        return register;
    }

    public String getValue(){
        return register;
    }
}
