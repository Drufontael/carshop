package tech.drufontael.carshop.model.converter;


import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import tech.drufontael.carshop.model.Register;

@Converter(autoApply = true)
public class RegisterConverter implements AttributeConverter<Register, String> {

    @Override
    public String convertToDatabaseColumn(Register register) {
        if (register == null) return null;
        return register.toString();  // Usa o toString() para salvar no banco
    }

    @Override
    public Register convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        return new Register(dbData);
    }
}
