package uz.pdp.marketcrm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.marketcrm.domain.entity.ProductEntity;
import uz.pdp.marketcrm.domain.entity.StoreEntity;

import java.util.Optional;
import java.util.UUID;

public interface StoreRepository extends JpaRepository<StoreEntity, UUID> {

}
