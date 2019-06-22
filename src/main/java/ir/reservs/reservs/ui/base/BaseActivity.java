package ir.reservs.reservs.ui.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import ir.reservs.reservs.ReserveApplication;
import ir.reservs.reservs.di.component.ActivityComponent;
import ir.reservs.reservs.di.component.DaggerActivityComponent;
import ir.reservs.reservs.di.module.ActivityModule;

public abstract class BaseActivity extends AppCompatActivity implements BaseContract.View {

    private ActivityComponent mActivityComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(ReserveApplication.getComponent())
                .build();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setup();
    }

    public ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }


    @Override
    public abstract void onError(String msg);

    public void onError(int resId) {
        onError(getString(resId));
    }

    ;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mActivityComponent = null;
    }

    public abstract void setup();
}
