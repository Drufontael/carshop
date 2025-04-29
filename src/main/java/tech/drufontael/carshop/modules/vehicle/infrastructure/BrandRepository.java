package tech.drufontael.carshop.modules.vehicle.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.drufontael.carshop.modules.vehicle.domain.Brand;

import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand,Long> {
    Optional<Brand> findByName(String name);
}
