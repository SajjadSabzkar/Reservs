package ir.reservs.reservs.di.component;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;
import ir.reservs.reservs.ReserveApplication;
import ir.reservs.reservs.data.DataManager;
import ir.reservs.reservs.di.module.ApplicationModule;
import ir.reservs.reservs.di.module.DataModule;
import ir.reservs.reservs.di.module.NetworkModule;
import ir.reservs.reservs.di.module.PreferenceModule;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        DataModule.class,
        NetworkModule.class,
        PreferenceModule.class
})
public interface ApplicationComponent {

    void inject(ReserveApplication app);

    Application application();

    DataManager getDataManager();
}
