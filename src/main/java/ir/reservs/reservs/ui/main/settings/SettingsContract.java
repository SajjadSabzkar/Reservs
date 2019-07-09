package ir.reservs.reservs.ui.main.settings;

import ir.reservs.reservs.ui.base.BaseFragmentContract;

public class SettingsContract {
    interface View extends BaseFragmentContract.View {
        void setUserInfo(String name, String phone, String image);

        void openLoginActivity();
    }

    interface Presenter extends BaseFragmentContract.Presenter<View> {
        void logoutUser();
    }
}
