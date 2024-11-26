package uz.pdp.marketcrm.integration.sms.eskiz;

import lombok.Data;

@Data
public class TokenData {
    private String message;
    private Token data;
    private String tokenType;

    @Data
    static class Token {
        private String token;
    }
}

