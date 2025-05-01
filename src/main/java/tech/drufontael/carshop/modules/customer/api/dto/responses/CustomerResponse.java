package tech.drufontael.carshop.modules.customer.api.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import tech.drufontael.carshop.modules.customer.domain.Customer;
import tech.drufontael.carshop.modules.customer.domain.enums.CustomerType;
import tech.drufontael.carshop.modules.shared.Contact;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class CustomerResponse {
    private String name;
    private String register;
    private String simplifiedAddress;
    private String simplifiedContact;
    private List<String> types;

    public static CustomerResponse fromDomain(Customer customer){
        StringBuilder address=new StringBuilder();
        if(customer.getAddress()!=null){
            address.append("Cidade: ").append(customer.getAddress().getCity()).append("%n");
            address.append("Estado: ").append(customer.getAddress().getState());
        }
        String contact="";
        if(customer.getContact()!=null){
            contact=simplifiedContactBuilder(customer.getContact());
        }

        List<String> newTypes = customer.getTypes().stream().map(CustomerType::name).toList();
        return new CustomerResponseBuilder()
                .name(customer.getName())
                .register(customer.getRegister().getValue())
                .simplifiedAddress(address.toString())
                .simplifiedContact(contact)
                .types(newTypes)
                .build();
    }

    private static String simplifiedContactBuilder(Contact contact) {
        StringBuilder builder=new StringBuilder();
        if(contact.getLandline()!=null) builder.append("Telefone: ")
                .append(contact.getLandline().getValue()).append("%n");
        if(contact.getCellPhone()!=null) builder.append("Celular: ")
                .append(contact.getCellPhone().getValue()).append("%n");
        if(contact.getEmail()!=null) builder.append("Email: ")
                .append(contact.getEmail().getValue());
        return builder.toString();
    }
}
