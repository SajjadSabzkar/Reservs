package ir.reservs.reservs.ui.main.settings;

import android.content.Intent;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import javax.inject.Inject;

import ir.reservs.reservs.data.DataManager;
import ir.reservs.reservs.ui.login.LoginActivity;

public class SettingsPresenter implements SettingsContract.Presenter {

    private DataManager dataManager;
    private SettingsContract.View view;
    private AppCompatActivity activity;

    @Inject
    public SettingsPresenter(DataManager dataManager, AppCompatActivity activity) {
        this.dataManager = dataManager;
        this.activity = activity;
    }

    @Override
    public void logoutUser() {
        dataManager.removeAccessToken();
        activity.startActivity(new Intent(activity, LoginActivity.class));
        activity.finish();
    }

    public void setUserProfileData() {
        view.setUserInfo(dataManager.getCurrentUserName(),
                dataManager.getCurrentUserPhone(),
                dataManager.getCurrentUserImage());

    }

    @Override
    public void onAttach(SettingsContract.View view) {
        this.view = view;
        Log.e("SettingsPresenter", "onAttach" + ": " + 1);
        setUserProfileData();
    }

    @Override
    public void onDetach() {
        view = null;
        activity = null;
    }
}
