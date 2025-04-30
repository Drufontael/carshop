package tech.drufontael.carshop.modules.vehicle.domain.value_object.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import tech.drufontael.carshop.modules.vehicle.domain.value_object.Renavam;

@Converter(autoApply = true)
public class RenavanConverter implements AttributeConverter<Renavam,String> {
    @Override
    public String convertToDatabaseColumn(Renavam renavam) {
        return renavam.getValue();
    }

    @Override
    public Renavam convertToEntityAttribute(String renavan) {
        return new Renavam(renavan);
    }
}
