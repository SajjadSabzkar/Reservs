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
import androidx.cardview.widget.CardView;
import androidx.navigation.fragment.NavHostFragment;

import javax.inject.Inject;

import ir.reservs.reservs.R;
import ir.reservs.reservs.ui.base.BaseFragment;

public class SettingsFragment extends BaseFragment implements SettingsContract.View {
    @Inject
    SettingsPresenter settingsPresenter;
    TextView txtName;
    TextView txtPhone;
    ImageView imgAvatar;
    CardView cardLogout, cardEdit, passwordCard;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_settings, container, false);
        txtName = view.findViewById(R.id.txtName);
        txtPhone = view.findViewById(R.id.txtPhone);
        imgAvatar = view.findViewById(R.id.imgAvatar);
        cardLogout = view.findViewById(R.id.cardLogout);
        cardEdit = view.findViewById(R.id.cardEdit);
        passwordCard = view.findViewById(R.id.passwordCard);
        cardLogout.setOnClickListener((v) -> settingsPresenter.logoutUser());
        cardEdit.setOnClickListener((v) -> NavHostFragment.findNavController(this).navigate(R.id.settingsToInformation));
        passwordCard.setOnClickListener((v) -> NavHostFragment.findNavController(this).navigate(R.id.settingsToPassword));
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

}
