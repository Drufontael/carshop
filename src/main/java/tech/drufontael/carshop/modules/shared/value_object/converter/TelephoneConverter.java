package tech.drufontael.carshop.modules.shared.value_object.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import tech.drufontael.carshop.modules.shared.value_object.Telephone;

@Converter(autoApply = true)
public class TelephoneConverter implements AttributeConverter<Telephone,String> {
    @Override
    public String convertToDatabaseColumn(Telephone telephone) {
        if (telephone==null) return "";
        return telephone.getValue();
    }

    @Override
    public Telephone convertToEntityAttribute(String telephone) {
        if (telephone.isBlank()) return null;
        return new Telephone(telephone);
    }
}
