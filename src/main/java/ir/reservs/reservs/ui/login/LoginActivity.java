package ir.reservs.reservs.ui.login;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dmax.dialog.SpotsDialog;
import ir.reservs.reservs.R;
import ir.reservs.reservs.ui.base.BaseActivity;
import ir.reservs.reservs.ui.main.MainActivity;

public class LoginActivity extends BaseActivity implements LoginContract.View {

    @BindView(R.id.txtPhone)
    EditText txtPhone;

    @BindView(R.id.txtPassword)
    EditText txtPassword;

    @Inject
    LoginPresenter loginPresenter;

    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);
        ButterKnife.bind(this);
    }

    @Override
    public void onError(int resId) {
        onError(getString(resId));
    }

    @Override
    public void setup() {
        super.setup();
        ButterKnife.bind(this);
        getActivityComponent().inject(this);
        loginPresenter.onAttach(this);
        dialog = new SpotsDialog.Builder()
                .setContext(this)
                .setCancelable(false)
                .setMessage(R.string.waiting)
                .build();
    }


    @OnClick(R.id.btnLogin)
    void onLoginClick() {
        Log.e("LoginActivity", "onLoginClick" + ": " + 1);
        loginPresenter.login(txtPhone.getText().toString(), txtPassword.getText().toString());
    }

    @Override
    public void openMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void showProgress() {
        dialog.show();
    }

    @Override
    public void hideProgress() {
        dialog.dismiss();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }
}
