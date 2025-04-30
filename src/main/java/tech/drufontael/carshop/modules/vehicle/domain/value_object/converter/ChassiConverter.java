package tech.drufontael.carshop.modules.vehicle.domain.value_object.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import tech.drufontael.carshop.modules.vehicle.domain.value_object.Chassi;

@Converter(autoApply = true)
public class ChassiConverter implements AttributeConverter<Chassi,String> {
    @Override
    public String convertToDatabaseColumn(Chassi chassi) {
        return chassi.getValue();
    }

    @Override
    public Chassi convertToEntityAttribute(String chassi) {
        return new Chassi(chassi);
    }
}
