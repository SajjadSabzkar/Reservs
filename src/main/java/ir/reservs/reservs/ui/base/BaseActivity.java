package ir.reservs.reservs.ui.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import ir.reservs.reservs.di.component.ApplicationComponent;
import ir.reservs.reservs.di.component.DaggerApplicationComponent;
import ir.reservs.reservs.di.module.ApiModule;
import ir.reservs.reservs.di.module.ApplicationModule;

public class BaseActivity extends AppCompatActivity {
    protected ApplicationComponent appComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(getApplication()))
                .apiModule(new ApiModule())
                .build();
        /*mApi = new AppApiHelper();
        mPref = new AppPreferencesHelper(this);
        mDataManager = new AppDataManager(mPref, mApi);
        disposable = new CompositeDisposable();*/
    }
}
