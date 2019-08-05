package ir.reservs.reservs.di.component;

import android.app.Application
import dagger.Component
import ir.reservs.reservs.ReserveApplication
import ir.reservs.reservs.data.DataManager
import ir.reservs.reservs.di.module.*
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ApplicationModule::class,
    DataModule::class,
    NetworkModule::class,
    PreferenceModule::class,
    DialogModule::class
])
interface ApplicationComponent {

    fun inject(app: ReserveApplication);

    fun application(): Application

    fun getDataManager(): DataManager
}
