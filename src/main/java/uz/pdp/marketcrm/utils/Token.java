package uz.pdp.marketcrm.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Token {
    private String token;
    private long tokenExpiryTime;

    public String getToken() {
        if (isTokenExpired()) {
            return null;
        }
        return token;
    }

    public void updateToken(String newToken, long expiresIn) {
        token = newToken;
        tokenExpiryTime = System.currentTimeMillis() + expiresIn * 1000;
    }

    public boolean isTokenExpired() {
        return System.currentTimeMillis() > tokenExpiryTime;
    }
}
