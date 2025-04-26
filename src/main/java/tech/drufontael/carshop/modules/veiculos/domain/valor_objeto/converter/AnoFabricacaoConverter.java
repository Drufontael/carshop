package tech.drufontael.carshop.modules.veiculos.domain.valor_objeto.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import tech.drufontael.carshop.modules.veiculos.domain.valor_objeto.AnoFabricacao;

@Converter(autoApply = true)
public class AnoFabricacaoConverter implements AttributeConverter<AnoFabricacao,Integer> {
    @Override
    public Integer convertToDatabaseColumn(AnoFabricacao anoFabricacao) {
        return anoFabricacao.getValue();
    }

    @Override
    public AnoFabricacao convertToEntityAttribute(Integer ano) {
        return new AnoFabricacao(ano);
    }
}
