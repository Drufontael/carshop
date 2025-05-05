package tech.drufontael.carshop.modules.customer.infrastructure;

import tech.drufontael.carshop.modules.customer.domain.Customer;

public interface CustomerManager {
    Customer create(String name, String register, String type);
    Customer getById(Long id);
    Customer update(Long id, Customer customer);
    void deleteById(Long id);
    void addContact(Long customerId, String landline, String cellPhone, String email);
    void addAddress(
            Long customerId,
            String cep,
            String street,
            String complement,
            String neighborhood,
            String city,
            String state,
            String conuntry
    );
    void addType(Long customerId, String type);
    <T> T doTypeAction(Long customerId,Long vehicleId,String type,String... args);
}
