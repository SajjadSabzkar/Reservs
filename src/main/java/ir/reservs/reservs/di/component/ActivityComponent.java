package ir.reservs.reservs.di.component;

import dagger.Component;
import ir.reservs.reservs.di.PerActivity;
import ir.reservs.reservs.di.module.ActivityModule;
import ir.reservs.reservs.ui.login.LoginActivity;
import ir.reservs.reservs.ui.main.MainActivity;
import ir.reservs.reservs.ui.splash.SplashActivity;


@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(SplashActivity activity);

    void inject(LoginActivity activity);

    void inject(MainActivity activity);


}

