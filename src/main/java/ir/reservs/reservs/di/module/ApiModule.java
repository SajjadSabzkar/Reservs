package ir.reservs.reservs.di.module;

import android.content.Context;
import android.text.TextUtils;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import ir.reservs.reservs.data.AppDataManager;
import ir.reservs.reservs.data.network.ApiHelper;
import ir.reservs.reservs.data.prefs.AppPreferencesHelper;
import ir.reservs.reservs.di.scope.ApplicationScope;
import ir.reservs.reservs.utils.Constants;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = ApplicationModule.class)
@Singleton
public class ApiModule {
    public ApiModule() {
    }

    @Provides
    @ApplicationScope
    ApiHelper provideApiService(Retrofit retrofit) {
        return retrofit.create(ApiHelper.class);
    }

    @Provides
    @ApplicationScope
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @ApplicationScope
    OkHttpClient provideOkHttpClient(Interceptor interceptor, Cache cache) {
        return new OkHttpClient.Builder()
                .cache(cache)
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .addInterceptor(interceptor).build();
    }

    @Provides
    @ApplicationScope
    Cache provideCache(File file) {
        return new Cache(file, 10 * 1000 * 1000);
    }

    @Provides
    @ApplicationScope
    File provideFile(Context context) {
        File file = new File(context.getFilesDir(), "cache_dir");
        if (!file.exists())
            file.mkdirs();
        return file;
    }

    @Provides
    @ApplicationScope
    Interceptor provideInterceptor(AppPreferencesHelper appPreferencesHelper) {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder requestBuilder = original.newBuilder()
                        .addHeader("Accept", "application/json")
                        .addHeader("Content-Type", "application/json");
                if (!TextUtils.isEmpty(appPreferencesHelper.getAccessToken())) {
                    requestBuilder.addHeader(
                            "Authorization",
                            "Bearer " + appPreferencesHelper.getAccessToken()
                    );
                }
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        };
    }

    @Provides
    @ApplicationScope
    AppPreferencesHelper provideAppPreferencesHelper(Context context) {
        return new AppPreferencesHelper(context);
    }

    @Provides
    @ApplicationScope
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    @ApplicationScope
    AppDataManager provideDatamanager(ApiHelper apiHelper, AppPreferencesHelper appPreferencesHelper) {
        return new AppDataManager(appPreferencesHelper, apiHelper);
    }
}
