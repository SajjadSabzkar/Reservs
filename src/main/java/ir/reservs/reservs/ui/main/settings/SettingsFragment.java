package ir.reservs.reservs.ui.main.settings;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.fragment.NavHostFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ir.reservs.reservs.R;
import ir.reservs.reservs.ui.base.BaseFragment;

public class SettingsFragment extends BaseFragment implements SettingsContract.View {
    @Inject
    SettingsPresenter settingsPresenter;

    @BindView(R.id.txtName)
    TextView txtName;

    @BindView(R.id.txtPhone)
    TextView txtPhone;

    @BindView(R.id.imgAvatar)
    ImageView imgAvatar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_settings, container, false);
        ButterKnife.bind(this, view);
        getActivityComponent().inject(this);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        settingsPresenter.onAttach(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (settingsPresenter != null) {
            settingsPresenter.onDetach();
        }
    }

    @Override
    public void setUserInfo(String name, String phone, String image) {
        Log.e("SettingsFragment", "setUserInfo" + ": " + phone);
        txtName.setText(name);
        txtPhone.setText(phone);
        //todo:load image into imgAvatar
    }

    @OnClick(R.id.cardLogout)
    void logoutClick() {
        settingsPresenter.logoutUser();
    }

    @OnClick(R.id.cardEdit)
    void editClick() {
        NavHostFragment.findNavController(this).navigate(R.id.settingsToInformation);
    }

    @OnClick(R.id.passwordCard)
    void passwordClick() {
        NavHostFragment.findNavController(this).navigate(R.id.settingsToPassword);
    }


}
