package ir.reservs.reservs.di.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ir.reservs.reservs.data.prefs.AppPreferencesHelper;
import ir.reservs.reservs.data.prefs.PreferencesHelper;

@Module
public class PreferenceModule {

    @Provides
    @Singleton
    PreferencesHelper providePreferenceHelper(Context context) {
        return new AppPreferencesHelper(context);
    }

    @Provides
    String provideToken(PreferencesHelper preferencesHelper) {
        return preferencesHelper.getAccessToken();
    }
}
