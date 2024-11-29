package uz.pdp.marketcrm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.marketcrm.domain.entity.UserEntity;
import uz.pdp.marketcrm.domain.response.UserResponse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
   Optional<UserEntity> findByUsername(String username);
    @Query("select u from users u")
    List<UserResponse> getAllUsers();

}
