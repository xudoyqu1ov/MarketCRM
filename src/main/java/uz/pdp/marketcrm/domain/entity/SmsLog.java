package uz.pdp.marketcrm.domain.entity;

import  jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import uz.pdp.marketcrm.domain.template.Id;

@Getter
@Setter
@Entity
@Table(name = "sms_log")
public class SmsLog extends Id {

    @Column(name = "phone_number", length = 25)
    private String phoneNumber;

    @Column(name = "message")
    private String message;

    @Column(name = "provider_response")
    private String providerResponse;

    @Column(name = "valid_second")
    private int validSecond;

    @Column(name = "used", columnDefinition = "boolean default false")
    private boolean used;
}

