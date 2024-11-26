package uz.pdp.marketcrm.integration.sms.eskiz;

import lombok.Data;

@Data
public class SmsSentData {
    private String id;
    private String status;
    private String message;
}