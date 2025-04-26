package tech.drufontael.carshop.modules.veiculos.domain.valor_objeto.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import tech.drufontael.carshop.modules.veiculos.domain.valor_objeto.Renavan;

@Converter(autoApply = true)
public class RenavanConverter implements AttributeConverter<Renavan,String> {
    @Override
    public String convertToDatabaseColumn(Renavan renavan) {
        return renavan.getvalue();
    }

    @Override
    public Renavan convertToEntityAttribute(String renavan) {
        return new Renavan(renavan);
    }
}
