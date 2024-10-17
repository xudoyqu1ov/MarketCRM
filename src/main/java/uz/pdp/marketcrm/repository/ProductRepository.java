package uz.pdp.marketcrm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.marketcrm.domain.entity.ProductEntity;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {
}
