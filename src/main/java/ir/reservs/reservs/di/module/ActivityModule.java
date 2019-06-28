package ir.reservs.reservs.di.module;

import android.content.Context;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.alirezaafkar.sundatepicker.DatePicker;

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
import ir.reservs.reservs.ui.main.information.InformationPresenter;
import ir.reservs.reservs.ui.main.password.PasswordPresenter;
import ir.reservs.reservs.ui.main.salons.SalonListAdapter;
import ir.reservs.reservs.ui.main.salons.SalonListPresenter;
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
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    @PerActivity
    SplashPresenter provideSplashPresenter(DataManager dataManager, CompositeDisposable disposable) {
        return new SplashPresenter(dataManager, disposable);
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    @PerActivity
    HistoryContract.Presenter provideHistoryPresenter(DataManager datamanager, CompositeDisposable disposable) {
        return new HistoryPresenter(datamanager, disposable);
    }


    @Provides
    HistoryAdapter provideHistoryAdapter() {
        return new HistoryAdapter(new ArrayList<>());
    }

    @Provides
    @PerActivity
    DatePicker provideDatePicker() {
        return new DatePicker();
    }

    @Provides
    @PerActivity
    DatePicker.Builder provideDatePickerBuilder() {
        return new DatePicker.Builder();
    }

   /* @Provides
    @PerActivity
    FragmentManager provideFragmentManager(AppCompatActivity activity) {
        return activity.getSupportFragmentManager();
    }*/

    @Provides
    @PerActivity
    AlertDialog.Builder provideAlertDialogBuilder(AppCompatActivity activity) {
        return new AlertDialog.Builder(activity);
    }

    @Provides
    SalonListAdapter provideSalonAdapter() {
        return new SalonListAdapter(new ArrayList<>());
    }

    @Provides
    @PerActivity
    SalonListPresenter provideSalonPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        return new SalonListPresenter(dataManager, compositeDisposable);
    }

    @Provides
    @PerActivity
    InformationPresenter provideInformationPresenter(DataManager dataManager,
                                                     CompositeDisposable compositeDisposable) {
        return new InformationPresenter(dataManager, compositeDisposable);
    }

    @Provides
    @PerActivity
    PasswordPresenter providePasswordPresenter(DataManager dataManager,
                                               CompositeDisposable compositeDisposable) {
        return new PasswordPresenter(dataManager, compositeDisposable);

    }
}
