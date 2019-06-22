package ir.reservs.reservs.ui.main.password;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.snackbar.Snackbar;

import javax.inject.Inject;

import ir.reservs.reservs.R;
import ir.reservs.reservs.ui.base.BaseFragment;

public class PasswordFragment extends BaseFragment implements PasswordContract.View {
    Button btnConfirm;
    @Inject
    PasswordPresenter passwordPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_change_password, container, false);
        getActivityComponent().inject(this);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        passwordPresenter.onAttach(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        passwordPresenter.onDetach();
    }

    @Override
    public void onError(String msg) {
        Snackbar.make(btnConfirm, msg, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void setup(View view) {
        btnConfirm = view.findViewById(R.id.btnConfirm);
        EditText txtCurrentPassword, txtNewPassword;
        txtCurrentPassword = view.findViewById(R.id.txtCurrentPassword);
        txtNewPassword = view.findViewById(R.id.txtNewPassword);
        btnConfirm.setOnClickListener(v -> {
            passwordPresenter.changePassword(
                    txtCurrentPassword.getText().toString(),
                    txtNewPassword.getText().toString()
            );
        });
    }

    @Override
    public void passwordChangedSuccessful() {
        onError(getString(R.string.your_password_changed));
        NavHostFragment.findNavController(this).popBackStack();
    }
}
