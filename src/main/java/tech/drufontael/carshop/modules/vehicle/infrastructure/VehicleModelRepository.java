package tech.drufontael.carshop.modules.vehicle.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.drufontael.carshop.modules.vehicle.domain.VehicleModel;

public interface VehicleModelRepository extends JpaRepository<VehicleModel,Long> {
}
