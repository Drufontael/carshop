package tech.drufontael.carshop.modules.customer.infrastructure;

import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import tech.drufontael.carshop.modules.customer.domain.Customer;
import tech.drufontael.carshop.modules.customer.domain.enums.CustomerType;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Long>, JpaSpecificationExecutor<Customer> {
    default List<Customer> findCustomerByType(CustomerType type){
        Specification<Customer> conjuction=(r,q,cb)->cb.conjunction();
        Specification<Customer> spec=Specification.where(conjuction);
        if(type!=null){
            Specification<Customer> typeEqual=(r,q,cb)-> {
                Join<Customer,CustomerType> join=r.join("types");
                return cb.equal(join,type);
            };
            spec=spec.and(typeEqual);
        }
        return findAll(spec);
    }
}
