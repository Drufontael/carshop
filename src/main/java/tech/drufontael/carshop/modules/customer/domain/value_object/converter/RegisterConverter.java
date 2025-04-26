package tech.drufontael.carshop.modules.customer.domain.value_object.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import tech.drufontael.carshop.modules.customer.domain.value_object.Register;

@Converter(autoApply = true)
public class RegisterConverter implements AttributeConverter<Register,String> {
    @Override
    public String convertToDatabaseColumn(Register register) {
        return register.getValue();
    }

    @Override
    public Register convertToEntityAttribute(String register) {
        return new Register(register);
    }
}
