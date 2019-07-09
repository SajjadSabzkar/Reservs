package ir.reservs.reservs.di.module;

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import ir.reservs.reservs.data.DataManager
import ir.reservs.reservs.di.ActivityContext
import ir.reservs.reservs.di.PerActivity
import ir.reservs.reservs.ui.splash.SplashPresenter

@Module
class ActivityModule(private val mActivity: AppCompatActivity) {


    @Provides
    @ActivityContext
    fun provideContext(): Context {
        return mActivity.baseContext!!
    }

    @Provides
    fun provideActivity(): AppCompatActivity {
        return mActivity
    }

    @Provides
    @PerActivity
    fun provideSplashPresenter(dataManager: DataManager, disposable: CompositeDisposable): SplashPresenter {
        return SplashPresenter(dataManager, disposable)
    }

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

}
