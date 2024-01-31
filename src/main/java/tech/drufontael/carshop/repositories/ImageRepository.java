package tech.drufontael.carshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.drufontael.carshop.model.Image;

public interface ImageRepository extends JpaRepository<Image,String> {
}
