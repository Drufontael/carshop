package tech.drufontael.carshop.modules.shared;

import jakarta.persistence.Embeddable;
import lombok.*;
import tech.drufontael.carshop.modules.shared.value_object.CEP;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Address {
    private CEP cep;
    private String street;
    private String complement;
    private String neighborhood;
    private String city;
    private String state;
    private String country;
}
