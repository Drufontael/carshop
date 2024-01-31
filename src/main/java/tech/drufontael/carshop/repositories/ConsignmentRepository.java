package tech.drufontael.carshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.drufontael.carshop.model.Consignment;

import java.util.UUID;

public interface ConsignmentRepository extends JpaRepository<Consignment, UUID> {
}
