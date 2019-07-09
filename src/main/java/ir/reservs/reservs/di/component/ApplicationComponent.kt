package ir.reservs.reservs.di.component;

import android.app.Application
import dagger.Component
import ir.reservs.reservs.ReserveApplication
import ir.reservs.reservs.data.DataManager
import ir.reservs.reservs.di.module.ApplicationModule
import ir.reservs.reservs.di.module.DataModule
import ir.reservs.reservs.di.module.NetworkModule
import ir.reservs.reservs.di.module.PreferenceModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ApplicationModule::class,
    DataModule::class,
    NetworkModule::class,
    PreferenceModule::class
])
interface ApplicationComponent {

    fun inject(app: ReserveApplication);

    fun application(): Application

    fun getDataManager(): DataManager
}
