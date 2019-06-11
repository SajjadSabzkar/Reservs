package ir.reservs.reservs.di.component;

import dagger.Component;
import ir.reservs.reservs.di.PerActivity;
import ir.reservs.reservs.di.module.ActivityModule;
import ir.reservs.reservs.ui.login.LoginActivity;
import ir.reservs.reservs.ui.main.MainActivity;
import ir.reservs.reservs.ui.main.history.HistoryFragment;
import ir.reservs.reservs.ui.main.reserve.ReserveFragment;
import ir.reservs.reservs.ui.main.settings.SettingsFragment;
import ir.reservs.reservs.ui.splash.SplashActivity;


@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(SplashActivity activity);

    void inject(LoginActivity activity);

    void inject(MainActivity activity);

    void inject(HistoryFragment fragment);

    void inject(SettingsFragment fragment);

    void inject(ReserveFragment fragment);
}

