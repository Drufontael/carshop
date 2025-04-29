package tech.drufontael.carshop.modules.vehicle.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.drufontael.carshop.modules.vehicle.domain.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle,Long> {
}
