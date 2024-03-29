package tech.drufontael.carshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.drufontael.carshop.model.Consignment;

import java.util.List;
import java.util.UUID;

public interface ConsignmentRepository extends JpaRepository<Consignment, UUID> {
    List<Consignment> findByConsignorRegister(String register);
    Consignment findByVehiclePlate(String plate);
}
