package tech.drufontael.carshop.modules.vehicle.domain.valor_objeto.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import tech.drufontael.carshop.modules.vehicle.domain.valor_objeto.ManufactureYear;

@Converter(autoApply = true)
public class AnoFabricacaoConverter implements AttributeConverter<ManufactureYear,Integer> {
    @Override
    public Integer convertToDatabaseColumn(ManufactureYear manufactureYear) {
        return manufactureYear.getValue();
    }

    @Override
    public ManufactureYear convertToEntityAttribute(Integer ano) {
        return new ManufactureYear(ano);
    }
}
