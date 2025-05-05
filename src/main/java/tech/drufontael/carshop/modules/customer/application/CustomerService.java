package tech.drufontael.carshop.modules.customer.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.drufontael.carshop.exceptions.ResourceNotFoundException;
import tech.drufontael.carshop.modules.customer.domain.Customer;
import tech.drufontael.carshop.modules.customer.domain.enums.CustomerType;
import tech.drufontael.carshop.modules.customer.infrastructure.CustomerManager;
import tech.drufontael.carshop.modules.customer.infrastructure.CustomerRepository;
import tech.drufontael.carshop.modules.shared.Address;
import tech.drufontael.carshop.modules.shared.CarshopConstants;
import tech.drufontael.carshop.modules.shared.Contact;
import tech.drufontael.carshop.modules.shared.value_object.CEP;
import tech.drufontael.carshop.modules.shared.value_object.Email;
import tech.drufontael.carshop.modules.shared.value_object.Register;
import tech.drufontael.carshop.modules.shared.value_object.Telephone;
import tech.drufontael.carshop.modules.vehicle.domain.Vehicle;
import tech.drufontael.carshop.modules.vehicle.infrastructure.VehicleManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService implements CustomerManager {

    private final CustomerRepository repository;
    private final VehicleManager vehicleManager;

    @Override
    @Transactional
    public Customer create(String name, String register, String type) {
        List<CustomerType> types=new ArrayList<>();
        types.add(CustomerType.valueOf(type.toUpperCase()));
        return repository.save(Customer.builder()
                        .name(name)
                        .register(new Register(register))
                        .types(types)
                .build());
    }

    @Override
    public Customer getById(Long id) {
        return repository.findById(id).orElseThrow(()->new ResourceNotFoundException("Cliente com id "+id+" não localizado!"));
    }

    @Override
    public Customer update(Long id, Customer customer) {
        Customer toUpdate=getById(id);
        toUpdate.setName(customer.getName());
        toUpdate.setRegister(customer.getRegister());
        toUpdate.setTypes(customer.getTypes());
        toUpdate.setContact(customer.getContact());
        toUpdate.setAddress(customer.getAddress());
        repository.save(toUpdate);
        return null;
    }

    @Override
    public void deleteById(Long id) {
        Customer toDelete=getById(id);
        repository.delete(toDelete);
    }

    @Override
    public void addContact(Long customerId, String landline, String cellPhone, String email) {
        Customer customer = getById(customerId);

        if (landline == null && cellPhone == null && email == null) return;

        customer.setContact(buildContact(landline, cellPhone, email));
        repository.save(customer);
    }



    @Override
    public void addAddress(Long customerId, String cep, String street, String complement,
                           String neighborhood, String city, String state, String country) {
        Customer customer = getById(customerId);

        if (cep == null && street == null && complement == null &&
                neighborhood == null && city == null && state == null && country == null) return;

        Address address = buildAddress(cep, street, complement, neighborhood, city, state, country);
        customer.setAddress(address);

        repository.save(customer);
    }


    @Override
    public void addType(Long customerId, String type) {
        Customer customer=getById(customerId);
        CustomerType newType=CustomerType.valueOf(type);
        if(!customer.getTypes().contains(newType)) customer.getTypes().add(newType);
        repository.save(customer);
    }

    @Override
    public <T> T doTypeAction(Long customerId,Long vehicleId,String type,String... args) {
        Customer customer = getById(customerId);
        Vehicle vehicle = vehicleManager.getVehicleById(vehicleId);
        return customer.doTypeAction(vehicle,type,args);
    }

    private Address buildAddress(String cep, String street, String complement,
                                 String neighborhood, String city, String state, String country) {
        Address address = new Address();

        Optional.ofNullable(cep).ifPresent(c -> address.setCep(new CEP(c)));
        Optional.ofNullable(street).ifPresent(address::setStreet);
        Optional.ofNullable(complement).ifPresent(address::setComplement);
        Optional.ofNullable(neighborhood).ifPresent(address::setNeighborhood);
        Optional.ofNullable(city).ifPresent(address::setCity);
        Optional.ofNullable(state).ifPresent(address::setState);
        Optional.ofNullable(country).ifPresent(address::setCountry);

        return address;
    }

    private Contact buildContact(String landline, String cellPhone, String email) {
        Contact contact = new Contact();
        Optional.ofNullable(landline).ifPresent(l -> contact.setLandline(new Telephone(l)));
        Optional.ofNullable(cellPhone).ifPresent(c -> contact.setCellPhone(new Telephone(c)));
        Optional.ofNullable(email).ifPresent(e -> contact.setEmail(new Email(e)));
        return contact;
    }


}
