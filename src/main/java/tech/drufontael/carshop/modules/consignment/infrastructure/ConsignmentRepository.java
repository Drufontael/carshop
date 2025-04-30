package tech.drufontael.carshop.modules.consignment.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.drufontael.carshop.modules.consignment.domain.Consignment;

public interface ConsignmentRepository extends JpaRepository<Consignment,Long> {
}
