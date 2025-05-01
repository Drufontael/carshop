package tech.drufontael.carshop.modules.shared.value_object.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import tech.drufontael.carshop.modules.shared.value_object.CEP;

@Converter(autoApply = true)
public class CEPConverter implements AttributeConverter<CEP,String> {
    @Override
    public String convertToDatabaseColumn(CEP cep) {
        if(cep==null) return "";
        return cep.getValue();
    }

    @Override
    public CEP convertToEntityAttribute(String cep) {
        if(cep.isBlank()) return null;
        return new CEP(cep);
    }
}
