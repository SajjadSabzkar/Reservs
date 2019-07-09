package ir.reservs.reservs.di.component;

import dagger.Component
import ir.reservs.reservs.di.PerActivity
import ir.reservs.reservs.di.module.ActivityModule
import ir.reservs.reservs.ui.login.LoginRegisterActivity
import ir.reservs.reservs.ui.main.MainActivity
import ir.reservs.reservs.ui.splash.SplashActivity


@PerActivity
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {
    fun inject(activity: SplashActivity)

    fun inject(activity: LoginRegisterActivity)

    fun inject(activity: MainActivity)

}

