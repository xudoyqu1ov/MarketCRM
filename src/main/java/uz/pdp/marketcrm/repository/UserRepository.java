package uz.pdp.marketcrm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.marketcrm.domain.entity.UserEntity;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

}
