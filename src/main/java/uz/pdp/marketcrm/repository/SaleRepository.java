package uz.pdp.marketcrm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.marketcrm.domain.entity.SaleEntity;

import java.util.UUID;

public interface SaleRepository extends JpaRepository<SaleEntity, UUID> {

}
