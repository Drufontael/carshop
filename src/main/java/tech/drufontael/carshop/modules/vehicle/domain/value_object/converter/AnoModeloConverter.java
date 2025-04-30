package tech.drufontael.carshop.modules.vehicle.domain.value_object.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import tech.drufontael.carshop.modules.vehicle.domain.value_object.ModelYear;

@Converter(autoApply = true)
public class AnoModeloConverter implements AttributeConverter<ModelYear,Integer> {
    @Override
    public Integer convertToDatabaseColumn(ModelYear modelYear) {
        return modelYear.getValue();
    }

    @Override
    public ModelYear convertToEntityAttribute(Integer ano) {
        return new ModelYear(ano);
    }
}
