package ir.reservs.reservs.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ir.reservs.reservs.R;
import ir.reservs.reservs.ui.base.BaseActivity;
import ir.reservs.reservs.ui.main.MainActivity;

public class LoginActivity extends BaseActivity implements ILoginView {
    @BindView(R.id.txtPhone)
    EditText txtPhone;
    @BindView(R.id.txtPassword)
    EditText txtPassword;

    @Inject
    LoginPresenter<LoginActivity> mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);
        ButterKnife.bind(this);
        init();
    }

    @OnClick(R.id.btnLogin)
    void onLoginClick() {
        mPresenter.onLogin(txtPhone.getText().toString(), txtPassword.getText().toString());
    }

    @Override
    public void onError(String error) {
        Snackbar.make(txtPassword, error, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onError(long resId) {
        Snackbar.make(txtPassword, (int) resId, Snackbar.LENGTH_LONG).show();
    }


    public void init() {
        //mPresenter = new LoginPresenter<>(appComponent.getDataManager(), appComponent.getCompositeDisposable());
        mPresenter.onAttach(this);
    }

    @Override
    public void openMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
