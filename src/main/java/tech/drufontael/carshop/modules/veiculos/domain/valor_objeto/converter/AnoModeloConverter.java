package tech.drufontael.carshop.modules.veiculos.domain.valor_objeto.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import tech.drufontael.carshop.modules.veiculos.domain.valor_objeto.AnoModelo;

@Converter(autoApply = true)
public class AnoModeloConverter implements AttributeConverter<AnoModelo,Integer> {
    @Override
    public Integer convertToDatabaseColumn(AnoModelo anoModelo) {
        return anoModelo.getValue();
    }

    @Override
    public AnoModelo convertToEntityAttribute(Integer ano) {
        return new AnoModelo(ano);
    }
}
