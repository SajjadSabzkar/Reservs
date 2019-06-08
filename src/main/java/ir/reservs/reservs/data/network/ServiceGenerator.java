package ir.reservs.reservs.data.network;

import android.text.TextUtils;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import ir.reservs.reservs.utils.Constants;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private ApiHelper apiHelperInstant;
    private Retrofit retrofit;

    public ServiceGenerator(String token) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();
                        Request.Builder requestBuilder = original.newBuilder()
                                .addHeader("Accept", "application/json")
                                .addHeader("Content-Type", "application/json");
                        if (!TextUtils.isEmpty(token)) {
                            requestBuilder.addHeader(
                                    "Authorization",
                                    "Bearer " + token
                            );
                        }
                        Request request = requestBuilder.build();
                        return chain.proceed(request);
                    }
                }).build();
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        apiHelperInstant = retrofit.create(ApiHelper.class);
    }

    public ApiHelper getApiService() {
        return apiHelperInstant;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}

