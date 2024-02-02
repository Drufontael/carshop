package tech.drufontael.carshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.RepresentationModel;
import tech.drufontael.carshop.model.Consignor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsignorDto  extends RepresentationModel<ConsignorDto> {
    private String name;
    private String address;
    private String register;
    private String identity;
    private String phone;

    public ConsignorDto(Consignor consignor) {
        BeanUtils.copyProperties(consignor,this);
    }

    public Consignor toConsignor(){
        return new Consignor(getName(),getAddress(),getRegister(),getIdentity(),getPhone());
    }
}
