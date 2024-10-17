package uz.pdp.marketcrm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.marketcrm.domain.entity.ReportEntity;

import java.util.UUID;

public interface ReportRepository extends JpaRepository<ReportEntity, UUID> {
}
