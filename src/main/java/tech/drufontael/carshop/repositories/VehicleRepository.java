package tech.drufontael.carshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.drufontael.carshop.model.Vehicle;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle,String> {
    List<Vehicle> findByActiveTrue();
    List<Vehicle> findByActiveFalse();
}
