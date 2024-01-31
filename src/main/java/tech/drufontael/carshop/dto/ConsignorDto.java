package tech.drufontael.carshop.dto;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.drufontael.carshop.model.Consignor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsignorDto {
    private String name;
    private String address;
    private String register;
    private String identity;
    private String phone;

    public Consignor toConsignor(){
        return new Consignor(getName(),getAddress(),getRegister(),getIdentity(),getPhone());
    }
}
