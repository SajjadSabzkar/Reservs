package ir.reservs.reservs.di.component;

import dagger.Component
import ir.reservs.reservs.di.PerActivity
import ir.reservs.reservs.di.module.ActivityModule
import ir.reservs.reservs.ui.main.MainActivity


@PerActivity
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(activity: MainActivity)

}

