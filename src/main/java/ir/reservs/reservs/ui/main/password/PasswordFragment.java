package ir.reservs.reservs.ui.main.password;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import javax.inject.Inject;

import ir.reservs.reservs.R;
import ir.reservs.reservs.ui.base.BaseFragment;

public class PasswordFragment extends BaseFragment implements PasswordContract.View {

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

    }
}
