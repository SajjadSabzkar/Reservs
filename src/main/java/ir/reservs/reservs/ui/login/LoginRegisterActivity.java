package ir.reservs.reservs.ui.login;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import dmax.dialog.SpotsDialog;
import ir.reservs.reservs.R;
import ir.reservs.reservs.ui.base.BaseActivity;
import ir.reservs.reservs.ui.login.login.LoginContract;
import ir.reservs.reservs.ui.login.login.LoginPresenter;
import ir.reservs.reservs.ui.main.MainActivity;

public class LoginRegisterActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login_register);
    }


    @Override
    public void onError(@NotNull String msg) {

    }

    @Override
    public void setup() {

    }
}
