package tech.drufontael.carshop.modules.customer.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.drufontael.carshop.modules.consignment.infrastructure.ConsignmentManager;
import tech.drufontael.carshop.modules.customer.api.dto.requests.CustomerRequest;
import tech.drufontael.carshop.modules.customer.api.dto.responses.CustomerResponse;
import tech.drufontael.carshop.modules.customer.domain.Customer;
import tech.drufontael.carshop.modules.customer.infrastructure.CustomerManager;
import tech.drufontael.carshop.modules.shared.dto.requests.AddressRequest;
import tech.drufontael.carshop.modules.shared.dto.requests.ContactRequest;
import tech.drufontael.carshop.modules.shared.utils.ControllerUtils;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v2/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerManager manager;

    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody CustomerRequest request){
        Customer customer=manager.create(request.name(), request.register(),request.type());
        URI location= ControllerUtils.buildUriFromCurrentRequest(customer.getId());
        return ResponseEntity.created(location).body(CustomerResponse.fromDomain(customer));
    }
    @PostMapping("/{id}/contact")
    public ResponseEntity<?> addCustomerContact(@PathVariable Long id, @RequestBody ContactRequest request){
        manager.addContact(id,request.landline(), request.cellPhone(), request.email());
        return ResponseEntity.ok().build();
    }
    @PostMapping("/{id}/address")
    public ResponseEntity<?> addCustomerAddress(@PathVariable Long id, @RequestBody AddressRequest request){
        manager.addAddress(id, request.cep(), request.city(), request.complement(), request.complement(),
                request.city(), request.state(), request.country());
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getAllByType(@RequestParam(value = "tipo",required = false) String type){
        List<Customer> customers=manager.getByType(type);
        return ResponseEntity.ok(customers.stream().map(CustomerResponse::fromDomain).toList());
    }

}
