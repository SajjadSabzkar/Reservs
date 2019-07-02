package ir.reservs.reservs.ui.main.settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.navigation.fragment.NavHostFragment;

import javax.inject.Inject;

import ir.reservs.reservs.R;
import ir.reservs.reservs.ui.base.BaseFragment;
import ir.reservs.reservs.ui.login.LoginActivity;

public class SettingsFragment extends BaseFragment implements SettingsContract.View {
    @Inject
    SettingsPresenter settingsPresenter;

    private TextView txtName;
    private TextView txtPhone;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_settings, container, false);
    }

    @Override
    public void onDestroyView() {
        if (settingsPresenter != null) {
            settingsPresenter.onDetach();
            settingsPresenter = null;
            txtName = null;
            txtPhone = null;
        }
        super.onDestroyView();
    }

    @Override
    public void setUserInfo(String name, String phone, String image) {
        txtName.setText(name);
        txtPhone.setText(phone);
        //todo:load image into imgAvatar
    }

    @Override
    public void openLoginActivity() {
        startActivity(new Intent(getActivity(), LoginActivity.class));
        getActivity().finish();
    }

    @Override
    public void onError(String msg) {

    }

    @Override
    public void setup(View view) {
        getFragmentComponent().inject(this);
        settingsPresenter.onAttach(this);
        txtName = view.findViewById(R.id.txtName);
        txtPhone = view.findViewById(R.id.txtPhone);
        settingsPresenter.setUserProfileData();
        //ImageView imgAvatar = view.findViewById(R.id.imgAvatar);
        CardView cardLogout = view.findViewById(R.id.cardLogout);
        CardView cardEdit = view.findViewById(R.id.cardEdit);
        CardView passwordCard = view.findViewById(R.id.passwordCard);
        cardLogout.setOnClickListener((v) -> settingsPresenter.logoutUser());
        cardEdit.setOnClickListener((v) -> NavHostFragment.findNavController(this).navigate(R.id.settingsToInformation));
        passwordCard.setOnClickListener((v) -> NavHostFragment.findNavController(this).navigate(R.id.settingsToPassword));
    }


}
