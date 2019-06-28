package ir.reservs.reservs.ui.main.settings;

import android.util.Log;

import javax.inject.Inject;

import ir.reservs.reservs.data.DataManager;

public class SettingsPresenter implements SettingsContract.Presenter {

    private DataManager dataManager;
    private SettingsContract.View view;

    @Inject
    public SettingsPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void logoutUser() {
        dataManager.removeAccessToken();
        view.openLoginActivity();
    }

    void setUserProfileData() {
        view.setUserInfo(dataManager.getCurrentUserName(),
                dataManager.getCurrentUserPhone(),
                dataManager.getCurrentUserImage());

    }

    @Override
    public void onAttach(SettingsContract.View view) {
        this.view = view;
    }

    @Override
    public void onDetach() {
        Log.e("SettingsPresenter","onDetach"+": "+"---------------");
        view = null;
    }
}
