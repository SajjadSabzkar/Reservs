package ir.reservs.reservs;

import android.app.Application;

import ir.reservs.reservs.di.component.ApplicationComponent;
import ir.reservs.reservs.di.component.DaggerApplicationComponent;
import ir.reservs.reservs.di.module.ApplicationModule;



public class ReserveApplication extends Application {
    private static ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();
    }

    public static ApplicationComponent getComponent() {
        return applicationComponent;
    }
}
