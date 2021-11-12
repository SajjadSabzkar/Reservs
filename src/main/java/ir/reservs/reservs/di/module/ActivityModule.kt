package ir.reservs.reservs.di.module;

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import ir.reservs.reservs.data.DataManager
import ir.reservs.reservs.di.ActivityContext
import ir.reservs.reservs.di.PerActivity

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
    fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

}
