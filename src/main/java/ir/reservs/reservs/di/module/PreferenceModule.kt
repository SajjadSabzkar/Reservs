package ir.reservs.reservs.di.module;

import android.content.Context
import dagger.Module
import dagger.Provides
import ir.reservs.reservs.data.prefs.AppPreferencesHelper
import ir.reservs.reservs.data.prefs.PreferencesHelper
import javax.inject.Singleton

@Module
class PreferenceModule {

    @Provides
    @Singleton
    fun providePreferenceHelper(context: Context): PreferencesHelper {
        return AppPreferencesHelper(context);
    }

    @Provides
    fun provideToken(preferencesHelper: PreferencesHelper): String? {
        return preferencesHelper.getAccessToken();
    }
}
