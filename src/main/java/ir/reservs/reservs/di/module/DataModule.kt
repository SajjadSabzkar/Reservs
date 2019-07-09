package ir.reservs.reservs.di.module;

import dagger.Module
import dagger.Provides
import ir.reservs.reservs.data.AppDataManager
import ir.reservs.reservs.data.DataManager
import ir.reservs.reservs.data.network.ApiHelper
import ir.reservs.reservs.data.prefs.PreferencesHelper
import javax.inject.Singleton

@Module
class DataModule {
    @Provides
    @Singleton
    fun provideDataManager(prefHelper: PreferencesHelper, apiHelper: ApiHelper): DataManager {
        return AppDataManager(prefHelper, apiHelper);
    }
}
