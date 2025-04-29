package tech.drufontael.carshop.modules.shared.value_object;

public class CEP {
    private String cep;

    public CEP(String cep){
        validarCEP(cep);
        this.cep=cepFormater(cep);
    }
    private void validarCEP(String cep) {
        if (cep == null) {
            throw new IllegalArgumentException("CEP inválido: não pode ser null");
        }
        String cepNumerico = cep.replaceAll("[^0-9]", "");
        if (cepNumerico.length() != 8) {
            throw new IllegalArgumentException("CEP inválido: " + cep + " (deve conter 8 dígitos)");
        }
        if (!cepNumerico.matches("\\d{8}")) {
            throw new IllegalArgumentException("CEP inválido: " + cep + " (deve conter apenas números)");
        }
        try {
            int numeroCEP = Integer.parseInt(cepNumerico);
            if (numeroCEP < 1000000 || numeroCEP > 99999999) {
                throw new IllegalArgumentException("CEP inválido: " + cep + " (fora do intervalo válido)");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("CEP inválido: " + cep + " (não é um número válido)");
        }
    }
    private String cepFormater(String cep){
        if(cep.length()==9) return cep;
        return cep.substring(0,3)+"-"+cep.substring(3);
    }

    public String getValue(){
        return this.cep;
    }
}
