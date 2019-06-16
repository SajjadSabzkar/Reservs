package ir.reservs.reservs.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.reservs.reservs.R;
import ir.reservs.reservs.data.DataManager;
import ir.reservs.reservs.ui.base.BaseActivity;
import ir.reservs.reservs.ui.login.LoginActivity;
import ir.reservs.reservs.ui.main.MainActivity;

public class SplashActivity extends BaseActivity implements SplashContract.View {

    @BindView(R.id.txtVersion)
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
        super.setup();
        ButterKnife.bind(this);
        getActivityComponent().inject(this);

        //todo : most inject not build here
        //splashPresenter = new SplashPresenter(dataManager, new CompositeDisposable());
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
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onError(String error) {
        Snackbar.make(txtVersion, error, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onError(int resId) {
        onError(getString(resId));
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        splashPresenter.onDetach();
        splashPresenter = null;
    }
}
