package uz.pdp.marketcrm.integration.sms.eskiz;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import retrofit2.converter.gson.GsonConverterFactory;
import uz.pdp.marketcrm.utils.LocalCache;
import uz.pdp.marketcrm.utils.Token;

import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Configuration
public class
RetrofitConfig {
    private final EskizProvider eskizProvider;
    private final LocalCache<String, Token> localCache = new LocalCache<>();
    private final String tokenKey = "token";

    public RetrofitConfig(@Lazy EskizProvider eskizProvider) {
        this.eskizProvider = eskizProvider;
    }

    @Bean("eskiz-retrofit-client")
    public retrofit2.Retrofit eskiz() {
        String url = "https://notify.eskiz.uz/";

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        httpClient.addInterceptor(new Interceptor() {
            @NotNull
            @Override
            public Response intercept(@NotNull Chain chain) throws IOException {

                Token token = localCache.get(tokenKey);
                if (token == null) {
                    String tokenString = eskizProvider.getToken();
                    token = new Token(tokenString, Instant.now().plus(29, ChronoUnit.DAYS).toEpochMilli());
                    localCache.put(tokenKey, token);
                }

                Request request = chain.request();
                if (token.getToken() != null) {
                    request = request.newBuilder()
                            .header("Authorization", "Bearer " + token.getToken())
                            .build();
                }

                Response response = chain.proceed(request);

                if (response.code() == 401 && token.getToken() != null) {
                    String tokenString = eskizProvider.refreshToken(token.getToken());
                    if (tokenString != null) {
                        token.setToken(tokenString);
                        token.setTokenExpiryTime(Instant.now().plus(29, ChronoUnit.DAYS).toEpochMilli());
                        localCache.put(tokenKey, token);

                        request = request.newBuilder().header("Authorization", "Bearer " + token.getToken()).build();
                        response = chain.proceed(request);
                    }
                }

                return response;
            }
        });

        return new retrofit2.Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
    }

    @Bean("eskiz-retrofit")
    public Retrofit eskizRetrofit(@Qualifier("eskiz-retrofit-client") retrofit2.Retrofit retrofit) {
        return retrofit.create(Retrofit.class);
    }

    @Bean("eskiz-token-cache")
    public LocalCache<String, Token> tokenLocalCache() {
        return new LocalCache<>();
    }
}