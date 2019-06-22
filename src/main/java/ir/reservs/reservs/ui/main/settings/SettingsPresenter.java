package ir.reservs.reservs.ui.main.settings;

import java.lang.ref.WeakReference;

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

    private void setUserProfileData() {
        view.setUserInfo(dataManager.getCurrentUserName(),
                dataManager.getCurrentUserPhone(),
                dataManager.getCurrentUserImage());

    }

    @Override
    public void onAttach(SettingsContract.View view) {
        this.view = view;
        setUserProfileData();
    }

    @Override
    public void onDetach() {
        view = null;
    }
}
