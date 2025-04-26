package tech.drufontael.carshop.modules.veiculos.domain.valor_objeto.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import tech.drufontael.carshop.modules.veiculos.domain.valor_objeto.Placa;

@Converter(autoApply = true)
public class PlacaConverter implements AttributeConverter<Placa,String> {
    @Override
    public String convertToDatabaseColumn(Placa placa) {
        return placa.getValue();
    }

    @Override
    public Placa convertToEntityAttribute(String placa) {
        return new Placa(placa);
    }
}
