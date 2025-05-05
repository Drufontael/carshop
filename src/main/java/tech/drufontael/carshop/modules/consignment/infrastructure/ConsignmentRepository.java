package tech.drufontael.carshop.modules.consignment.infrastructure;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import tech.drufontael.carshop.modules.consignment.domain.Consignment;
import tech.drufontael.carshop.modules.customer.domain.Customer;
import tech.drufontael.carshop.modules.vehicle.domain.Vehicle;

import java.util.List;

public interface ConsignmentRepository extends JpaRepository<Consignment,Long>, JpaSpecificationExecutor<Consignment> {
    default public List<Consignment> findByConsignorAndVehicle(Customer consignor, Vehicle vehicle){
        Specification<Consignment> conjuction=(root,query,cb)-> cb.conjunction();
        Specification<Consignment> spec = Specification.where(conjuction);
        if(consignor!=null){
            Specification<Consignment> consignorEqual=(root,query,cb)->
                    cb.equal(root.get("consignor"),consignor);
            spec=spec.and(consignorEqual);
        }
        if(vehicle!=null){
            Specification<Consignment> vehicleEqual=(root,query,cb)->
                    cb.equal(root.get("vehicle"),vehicle);
            spec=spec.and(vehicleEqual);
        }
        return findAll(spec);
    }
}
