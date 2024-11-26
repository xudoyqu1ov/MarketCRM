package uz.pdp.marketcrm.integration.sms.eskiz;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;

public interface Retrofit {
    @POST("/api/auth/login")
    Call<TokenData> getToken(@Body RequestBody body);

    @PATCH("/api/auth/refresh")
    Call<TokenData> refreshToken(@Header("Authorization") String token);

    @POST("/api/message/sms/send")
    Call<SmsSentData> sendSms(@Body RequestBody body);
}