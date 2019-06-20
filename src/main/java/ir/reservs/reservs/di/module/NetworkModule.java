package ir.reservs.reservs.di.module;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ir.reservs.reservs.data.network.ApiHelper;
import ir.reservs.reservs.data.prefs.PreferencesHelper;
import ir.reservs.reservs.utils.Constants;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @Provides
    @Singleton
    ApiHelper provideApiHelper(Retrofit retrofit) {
        return retrofit.create(ApiHelper.class);
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Interceptor interceptor) {
        return new OkHttpClient.Builder()
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .addInterceptor(interceptor).build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient, Converter.Factory converter) {
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(converter)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }


    @Provides
    @Singleton
    Interceptor provideInterceptor(PreferencesHelper appPreferencesHelper) {
        return chain -> {
            Request original = chain.request();
            Request.Builder requestBuilder = original.newBuilder()
                    .addHeader("Accept", "application/json")
                    .addHeader("Content-Type", "application/json");
            String token = appPreferencesHelper.getAccessToken();
            if (!TextUtils.isEmpty(token)) {
                requestBuilder.addHeader(
                        "Authorization",
                        "Bearer " + token
                );
            }
            Request request = requestBuilder.build();
            return chain.proceed(request);
        };
    }

    @Provides
    @Singleton
    static Converter.Factory provideGsonConverterFactory(Gson gson) {
        return GsonConverterFactory.create(gson);
    }

    @Provides
    @Singleton
    static Gson provideGson() {
        return new Gson();
    }
}
