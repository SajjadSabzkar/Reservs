package ir.reservs.reservs.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ir.reservs.reservs.data.AppDataManager;
import ir.reservs.reservs.data.DataManager;
import ir.reservs.reservs.data.network.ApiHelper;
import ir.reservs.reservs.data.prefs.PreferencesHelper;

@Module
public class DataModule {
    @Provides
    @Singleton
    DataManager provideDataManager(PreferencesHelper prefHelper, ApiHelper apiHelper) {
        return new AppDataManager(prefHelper, apiHelper);
    }
}
