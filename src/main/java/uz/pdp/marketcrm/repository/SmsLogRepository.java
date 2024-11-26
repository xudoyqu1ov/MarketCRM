package uz.pdp.marketcrm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.marketcrm.domain.entity.SmsLog;

import java.util.List;

public interface SmsLogRepository extends JpaRepository<SmsLog, Long> {

    @Query("select sl from SmsLog sl where sl.phoneNumber = :phone and sl.message = :code and sl.used = false order by sl.createdDate desc")
    List<SmsLog> findSmsLog(@Param("phone") String phone, @Param("code") String code);

    @Modifying
    @Query("update SmsLog sl set sl.used = true where sl.id = :id")
    void markIsUsed(@Param("id") Long id);
}