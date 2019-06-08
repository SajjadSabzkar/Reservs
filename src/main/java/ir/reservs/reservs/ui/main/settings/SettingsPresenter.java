package ir.reservs.reservs.ui.main.settings;

import ir.reservs.reservs.data.DataManager;

public class SettingsPresenter implements ISettingsPresenter {

    private DataManager dataManager;

    SettingsPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void logoutUser() {
        dataManager.removeAccessToken();
    }
}
