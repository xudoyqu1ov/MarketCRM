package uz.pdp.marketcrm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.marketcrm.domain.entity.ProductEntity;
import uz.pdp.marketcrm.domain.enumurators.ProductCategory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {
    boolean existsByName(String name);
    Optional<ProductEntity> findByName(String name);
    List<ProductEntity> findAllByCategory(ProductCategory category);
}
