package tech.drufontael.carshop.modules.shared.value_object.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import tech.drufontael.carshop.modules.shared.value_object.Telephone;

@Converter(autoApply = true)
public class TelephoneConverter implements AttributeConverter<Telephone,String> {
    @Override
    public String convertToDatabaseColumn(Telephone telephone) {
        return telephone.getValue();
    }

    @Override
    public Telephone convertToEntityAttribute(String telephone) {
        return new Telephone(telephone);
    }
}
