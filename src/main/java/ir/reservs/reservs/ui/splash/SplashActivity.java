package ir.reservs.reservs.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.reservs.reservs.R;
import ir.reservs.reservs.ui.base.BaseActivity;
import ir.reservs.reservs.ui.login.LoginActivity;
import ir.reservs.reservs.ui.main.MainActivity;

public class SplashActivity extends BaseActivity implements ISplashView {
    @BindView(R.id.txtVersion)
    TextView txtVersion;

    SplashPresenter<SplashActivity> mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_splash);
        ButterKnife.bind(this);
        init();
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
    public void onError(long resId) {
        onError(getString((int) resId));
    }


    public void init() {
        mPresenter = new SplashPresenter<>(appComponent.getDataManager(), appComponent.getCompositeDisposable());
        mPresenter.onAttach(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter = null;
    }
}
