package ir.reservs.reservs.ui.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import ir.reservs.reservs.ReserveApplication;
import ir.reservs.reservs.di.component.ActivityComponent;
import ir.reservs.reservs.di.component.DaggerActivityComponent;
import ir.reservs.reservs.di.module.ActivityModule;

public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    private ActivityComponent mActivityComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(ReserveApplication.getComponent())
                .build();
    }

    public ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }

    @Override
    public void setup() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        setup();
    }

    @Override
    public void onError(String msg) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mActivityComponent = null;
    }
}
