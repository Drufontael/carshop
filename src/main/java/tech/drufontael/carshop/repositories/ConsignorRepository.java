package tech.drufontael.carshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.drufontael.carshop.model.Consignor;

public interface ConsignorRepository extends JpaRepository<Consignor,String> {
}
