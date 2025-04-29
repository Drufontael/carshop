package tech.drufontael.carshop.modules.vehicle.domain.valor_objeto.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import tech.drufontael.carshop.modules.vehicle.domain.valor_objeto.Plate;

@Converter(autoApply = true)
public class PlacaConverter implements AttributeConverter<Plate,String> {
    @Override
    public String convertToDatabaseColumn(Plate plate) {
        return plate.getValue();
    }

    @Override
    public Plate convertToEntityAttribute(String placa) {
        return new Plate(placa);
    }
}
