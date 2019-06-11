package ir.reservs.reservs.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ir.reservs.reservs.R;
import ir.reservs.reservs.ui.main.MainActivity;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    @BindView(R.id.txtPhone)
    EditText txtPhone;

    @BindView(R.id.txtPassword)
    EditText txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnLogin)
    void onLoginClick() {

    }

    @Override
    public void openMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
