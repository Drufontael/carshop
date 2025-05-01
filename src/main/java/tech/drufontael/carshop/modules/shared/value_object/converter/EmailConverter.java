package tech.drufontael.carshop.modules.shared.value_object.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import tech.drufontael.carshop.modules.shared.value_object.Email;

@Converter(autoApply = true)
public class EmailConverter implements AttributeConverter<Email,String> {
    @Override
    public String convertToDatabaseColumn(Email email) {
        if(email==null) return "";
        return email.getValue();
    }

    @Override
    public Email convertToEntityAttribute(String email) {
        if(email.isBlank()) return null;
        return new Email(email);
    }
}
