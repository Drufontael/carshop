package tech.drufontael.carshop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tb_consignor")
public class Consignor extends RepresentationModel<Consignor> {
    private String name;
    private String address;
    @Id
    private String register;
    private String identity;
    private String phone;

}
