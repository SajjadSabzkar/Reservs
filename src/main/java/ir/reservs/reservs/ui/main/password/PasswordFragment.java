package ir.reservs.reservs.ui.main.password;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import butterknife.ButterKnife;
import ir.reservs.reservs.R;
import ir.reservs.reservs.ui.base.BaseFragment;

public class PasswordFragment extends BaseFragment implements PasswordContract.View {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_password, container, false);
        ButterKnife.bind(this, view);
        getActivityComponent().inject(this);
        return view;
    }
}
