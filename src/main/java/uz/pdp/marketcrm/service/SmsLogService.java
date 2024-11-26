package uz.pdp.marketcrm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.marketcrm.domain.entity.SmsLog;
import uz.uzinfoweb.backendapp.repository.db.SmsLogRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SmsLogService {
    private final SmsLogRepository smsLogRepository;

    @Value(value = "${app.sms.valid-second}")
    private int smsValidSecond;

    public SmsLog createSmsLog(String phoneNumber, String messageTobeSaved, Object response) {
        SmsLog smsLog = new SmsLog();
        smsLog.setPhoneNumber(phoneNumber);
        smsLog.setMessage(messageTobeSaved);
        smsLog.setProviderResponse(String.valueOf(response));
        smsLog.setValidSecond(smsValidSecond);
        return smsLogRepository.save(smsLog);
    }

    @Transactional
    public Optional<SmsLog> validateSms(String phone, String code) {
        List<SmsLog> smsLogs = smsLogRepository.findSmsLog(phone, code);
        if (smsLogs.isEmpty()) {
            return Optional.empty();
        }
        SmsLog smsLog = smsLogs.getFirst();
        Instant logCreatedDate = smsLog.getCreatedDate();

        if (logCreatedDate.isBefore(Instant.now().minusSeconds(smsValidSecond))) {
            markSmsIsUsed(smsLog.getId());
            return Optional.empty();
        }

        return Optional.ofNullable(smsLogs.getFirst());
    }

    @Transactional
    public void markSmsIsUsed(Long id) {
        smsLogRepository.markIsUsed(id);
    }
}