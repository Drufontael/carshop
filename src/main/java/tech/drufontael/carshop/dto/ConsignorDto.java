package tech.drufontael.carshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.RepresentationModel;
import tech.drufontael.carshop.model.Consignor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsignorDto  extends RepresentationModel<ConsignorDto> {
    private UUID id;
    private String name;
    private String address;
    private String register;
    private String identity;
    private String phone;

    public ConsignorDto(Consignor consignor) {
        BeanUtils.copyProperties(consignor,this);
    }

    public Consignor toConsignor(){
        return new Consignor(getId(),getName(),getAddress(),getRegister(),getIdentity(),getPhone());
    }
}
