package tech.drufontael.carshop.modules.customer.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.drufontael.carshop.modules.customer.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
