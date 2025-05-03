package tech.drufontael.carshop.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import tech.drufontael.carshop.modules.customer.domain.Customer;
import tech.drufontael.carshop.modules.customer.domain.enums.CustomerType;
import tech.drufontael.carshop.modules.customer.infrastructure.CustomerRepository;
import tech.drufontael.carshop.modules.shared.Address;
import tech.drufontael.carshop.modules.shared.Contact;
import tech.drufontael.carshop.modules.shared.value_object.CEP;
import tech.drufontael.carshop.modules.shared.value_object.Email;
import tech.drufontael.carshop.modules.shared.value_object.Register;
import tech.drufontael.carshop.modules.shared.value_object.Telephone;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final CustomerRepository repository;




    @Override
    public void run(String... args) throws Exception {
        List<CustomerType> types=new ArrayList<>();
        types.add(CustomerType.OWNER);
        Contact contact=new Contact(null,new Telephone("62984471302"),new Email("eduardonunes@hotmail.com"));
        Address address=Address.builder()
                .cep(new CEP("74215-240"))
                .street("Av. Multirão")
                .complement("Qd.78, Lt.23E")
                .neighborhood("Setor Bueno")
                .city("Goiânia")
                .state("GO")
                .country("Brasil")
                .build();
        Customer customer=Customer.builder()
                .name("MM Motors")
                .register(new Register("43.711.907/0001-71"))
                .contact(contact)
                .address(address)
                .types(types)
                .build();
        //repository.save(customer);
    }
}
