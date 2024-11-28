package uz.pdp.marketcrm.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.marketcrm.domain.entity.ProductBoxEntity;
import uz.pdp.marketcrm.domain.entity.ProductEntity;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductBoxRepository extends JpaRepository<ProductBoxEntity, UUID> {
    Optional<ProductBoxEntity> findByProductId(UUID productId);
}
