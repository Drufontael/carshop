package tech.drufontael.carshop.modules.shared.value_object.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import tech.drufontael.carshop.modules.shared.value_object.Register;

@Converter(autoApply = true)
public class RegisterConverter implements AttributeConverter<Register,String> {
    @Override
    public String convertToDatabaseColumn(Register register) {
        if(register==null) return "";
        return register.getValue();
    }

    @Override
    public Register convertToEntityAttribute(String register) {
        if(register.isBlank()) return null;
        return new Register(register);
    }
}
