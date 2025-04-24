package tech.drufontael.carshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.RepresentationModel;
import tech.drufontael.carshop.model.Consignor;
import tech.drufontael.carshop.utils.Utils;

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
        this.id=consignor.getId();
        this.name=consignor.getName();
        this.address=consignor.getAddress();
        this.register=Utils.registerFormat(consignor.getRegister());
        this.identity=consignor.getIdentity();
        this.phone=consignor.getPhone();
    }

    public Consignor toConsignor(){
        String register= Utils.registerFormat(this.getRegister());
        return new Consignor(getId(),getName(),getAddress(),register,getIdentity(),getPhone());
    }
}
