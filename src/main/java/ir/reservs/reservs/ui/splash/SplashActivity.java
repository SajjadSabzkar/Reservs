package ir.reservs.reservs.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import javax.inject.Inject;

import ir.reservs.reservs.R;
import ir.reservs.reservs.data.DataManager;
import ir.reservs.reservs.ui.base.BaseActivity;
import ir.reservs.reservs.ui.login.LoginRegisterActivity;
import ir.reservs.reservs.ui.main.MainActivity;

public class SplashActivity extends BaseActivity implements SplashContract.View {

    TextView txtVersion;

    @Inject
    DataManager dataManager;

    //
    @Inject
    SplashPresenter splashPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_splash);
    }

    @Override
    public void setup() {
        txtVersion = findViewById(R.id.txtVersion);
        getActivityComponent().inject(this);

        splashPresenter.onAttach(this);
    }

    @Override
    public void openMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void openLoginActivity() {
        Intent i = new Intent(this, LoginRegisterActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onError(String error) {
        Snackbar.make(txtVersion, error, Snackbar.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (splashPresenter != null) {
            splashPresenter.onDetach();
            splashPresenter = null;
        }
    }

    @Override
    public void emptyState() {

    }

    @Override
    public void errorState() {

    }

    @Override
    public void loadingState() {

    }

    @Override
    public void normalState() {

    }
}
