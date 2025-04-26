package tech.drufontael.carshop.modules.customer.domain;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import tech.drufontael.carshop.modules.customer.domain.enums.CustomerType;
import tech.drufontael.carshop.modules.customer.domain.value_object.Register;

import java.util.List;

public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    protected String nome;
    protected Register register;
    protected List<CustomerType> types;
}
