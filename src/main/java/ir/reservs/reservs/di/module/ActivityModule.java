package ir.reservs.reservs.di.module;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import ir.reservs.reservs.data.DataManager;
import ir.reservs.reservs.di.ActivityContext;
import ir.reservs.reservs.di.PerActivity;
import ir.reservs.reservs.ui.main.history.HistoryAdapter;
import ir.reservs.reservs.ui.main.history.HistoryContract;
import ir.reservs.reservs.ui.main.history.HistoryPresenter;
import ir.reservs.reservs.ui.splash.SplashPresenter;

@Module
public class ActivityModule {
    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    @PerActivity
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    @PerActivity
    SplashPresenter provideSplashPresenter(DataManager dataManager, CompositeDisposable disposable) {
        return new SplashPresenter(dataManager, disposable);
    }

    @Provides
    @PerActivity
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    @PerActivity
    HistoryContract.Presenter provideHistoryPresenter(DataManager datamanager, CompositeDisposable disposable) {
        return new HistoryPresenter(datamanager, disposable);
    }

    @Provides
    @PerActivity
    HistoryAdapter provideHistoryAdapter() {
        return new HistoryAdapter(new ArrayList<>());
    }

}
