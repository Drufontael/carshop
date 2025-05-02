package tech.drufontael.carshop.modules.vehicle.infrastructure;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import tech.drufontael.carshop.modules.vehicle.domain.Brand;
import tech.drufontael.carshop.modules.vehicle.domain.VehicleModel;

import java.util.List;

public interface VehicleModelRepository extends JpaRepository<VehicleModel,Long>, JpaSpecificationExecutor<VehicleModel> {

    default List<VehicleModel> findByBrand(Brand brand){
        Specification<VehicleModel> conjuction=(root, q, cB) -> cB.conjunction();
        Specification<VehicleModel> spec=Specification.where(conjuction);
        if(brand!=null){
            Specification<VehicleModel> brandEqual = (root,q,cB) ->
                    cB.equal(root.get("brand"),brand);
            spec=spec.and(brandEqual);
        }
        return findAll(spec);
    }
}
