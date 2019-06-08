package ir.reservs.reservs.di.component;

import android.content.Context;

import dagger.Component;
import io.reactivex.disposables.CompositeDisposable;
import ir.reservs.reservs.data.AppDataManager;
import ir.reservs.reservs.data.network.ApiHelper;
import ir.reservs.reservs.data.prefs.AppPreferencesHelper;
import ir.reservs.reservs.di.module.ApiModule;
import ir.reservs.reservs.di.module.ApplicationModule;
import ir.reservs.reservs.di.scope.ApplicationScope;

@ApplicationScope
@Component(modules = {ApplicationModule.class, ApiModule.class})
public interface ApplicationComponent {

    Context getContext();

    ApiHelper getApiService();

    AppPreferencesHelper getAppPreferencesHelper();

    CompositeDisposable getCompositeDisposable();

    AppDataManager getDataManager();
}
