package tech.drufontael.carshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.drufontael.carshop.model.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle,String> {
}
