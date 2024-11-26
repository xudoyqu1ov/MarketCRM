package uz.pdp.marketcrm.integration.sms.eskiz;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Response;

@Slf4j
@Component
@RequiredArgsConstructor
public class EskizProvider {
    @Value("${app.sms.eskiz.login}")
    private String login;
    @Value("${app.sms.eskiz.password}")
    private String password;

    private final Retrofit retrofit;

    public SmsSentData sendSms(String phoneNumber, String message) {
        SmsSentData res = null;
        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart("mobile_phone", phoneNumber).addFormDataPart("message", message).build();

        Call<SmsSentData> smsSentDataCall = retrofit.sendSms(requestBody);
        try {
            Response<SmsSentData> smsSentDataResponse = smsSentDataCall.execute();
            res = smsSentDataResponse.body();
        } catch (Exception e) {
            log.error("Eskiz send sms error: {}", e.getMessage());
        }
        return res;
    }

    public String getToken() {
        String tokenToReturn = "";
        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart("email", login).addFormDataPart("password", password).build();

        Call<TokenData> tokenDataCall = retrofit.getToken(requestBody);
        try {
            Response<TokenData> tokenDataResponse = tokenDataCall.execute();
            TokenData data = tokenDataResponse.body();
            if (data != null && data.getData() != null && data.getData().getToken() != null) {
                tokenToReturn = data.getData().getToken();
            }

        } catch (Exception e) {
            log.error("Eskiz get token error: {}", e.getMessage());
        }

        return tokenToReturn;
    }

    public String refreshToken(String token) {
        String tokenToReturn = "";
        Call<TokenData> tokenDataCall = retrofit.refreshToken(token);
        try {
            Response<TokenData> tokenDataResponse = tokenDataCall.execute();
            TokenData data = tokenDataResponse.body();
            if (data != null && data.getData() != null && data.getData().getToken() != null) {
                tokenToReturn = data.getData().getToken();
            }
        } catch (Exception e) {
            log.error("Eskiz refresh token error: {}", e.getMessage());
        }
        return tokenToReturn;
    }
}
