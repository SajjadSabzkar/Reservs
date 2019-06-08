package ir.reservs.reservs.di.module;

import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;
import ir.reservs.reservs.di.scope.ApplicationScope;

@Module
public class ApplicationModule {
    private Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }


    @Provides
    @ApplicationScope
    Context provideApplication() {
        return mApplication;
    }

}
