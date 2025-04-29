package tech.drufontael.carshop.modules.customer.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.drufontael.carshop.modules.customer.domain.enums.CustomerType;
import tech.drufontael.carshop.modules.shared.Address;
import tech.drufontael.carshop.modules.shared.Contact;
import tech.drufontael.carshop.modules.shared.value_object.Register;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    protected String name;
    protected Register register;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "customer_types", joinColumns = @JoinColumn(name = "customer_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    protected List<CustomerType> types;
    @Embedded
    protected Contact contact;
    @Embedded
    protected Address address;
}
