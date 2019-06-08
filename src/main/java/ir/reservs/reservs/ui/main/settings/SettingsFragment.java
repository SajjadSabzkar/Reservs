package ir.reservs.reservs.ui.main.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import ir.reservs.reservs.R;
import ir.reservs.reservs.ui.main.base.BaseFragment;

public class SettingsFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_settings, container, false);
        (view.findViewById(R.id.cardLogout)).setOnClickListener(i -> openLoginActivity());
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        appComponent = null;
    }

    void openLoginActivity() {

    }
}
