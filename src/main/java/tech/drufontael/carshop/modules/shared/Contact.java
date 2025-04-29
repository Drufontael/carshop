package tech.drufontael.carshop.modules.shared;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.drufontael.carshop.modules.shared.value_object.Email;
import tech.drufontael.carshop.modules.shared.value_object.Telephone;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Contact {
    private Telephone landline;
    private Telephone cellPhone;
    private Email email;
}
