package tech.drufontael.carshop.modules.customer.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.drufontael.carshop.modules.customer.api.dto.requests.CustomerRequest;
import tech.drufontael.carshop.modules.customer.api.dto.responses.CustomerResponse;
import tech.drufontael.carshop.modules.customer.domain.Customer;
import tech.drufontael.carshop.modules.customer.infrastructure.CustomerManager;
import tech.drufontael.carshop.modules.shared.utils.ControllerUtils;

import java.net.URI;

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

}
