package uz.pdp.marketcrm.integration.sms;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.pdp.marketcrm.common.Localization;
import uz.pdp.marketcrm.constant.MessageKeys;
import uz.pdp.marketcrm.domain.entity.SmsLog;
import uz.pdp.marketcrm.exception.CustomException;
import uz.pdp.marketcrm.integration.sms.eskiz.EskizProvider;
import uz.pdp.marketcrm.service.SmsLogService;

import java.time.Instant;
import java.util.Random;

@Service
@Slf4j
@RequiredArgsConstructor
public class SmsProvider {
    private final EskizProvider eskizProvider;
    private final SmsLogService smsLogService;

    private static final String SMS_MESSAGE_CONTENT = "IshborUz verification code: %s";
    private final Localization localization;

    // todo make more realistic code generation in here
    @Transactional
    public SmsLog sendSms(String phoneNumber) {
        Random random = new Random(Instant.now().getEpochSecond());
        String messageTobeSaved = String.valueOf((random.nextInt(90000) + 10000));
        String messageTobeSent = String.format(SMS_MESSAGE_CONTENT, messageTobeSaved);
        Object response = eskizProvider.sendSms(phoneNumber, messageTobeSent);
        return smsLogService.createSmsLog(phoneNumber, messageTobeSaved, response);
    }

    public SmsLog validateSms(String phone, String code) {
        return smsLogService.validateSms(phone, code).orElseThrow(() -> new CustomException(
                localization.localize(MessageKeys.SMS_WRONG_OR_LATE),
                MessageKeys.SMS_WRONG_OR_LATE,
                HttpStatus.BAD_REQUEST
        ));
    }

    public void markIsUsed(Long id) {
        smsLogService.markSmsIsUsed(id);
    }
}


