package ir.reservs.reservs.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

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
        //Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


}
