package ir.reservs.reservs;

import android.app.Application;

import ir.reservs.reservs.di.component.ApplicationComponent;
import ir.reservs.reservs.di.component.DaggerApplicationComponent;
import ir.reservs.reservs.di.module.ApiModule;
import ir.reservs.reservs.di.module.ApplicationModule;


/**
 * Created by mhrezai on 08/10/2018.
 */

public class ReserveApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationComponent applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .apiModule(new ApiModule())
                .build();
    }
}
