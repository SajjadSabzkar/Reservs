package ir.reservs.reservs.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val mApplication: Application) {

    @Provides
    @Singleton
    fun provideContext(): Context {
        return mApplication.applicationContext
    }

    @Provides
    @Singleton
    fun provideApplication(): Application {
        return mApplication
    }

}
