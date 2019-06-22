package ir.reservs.reservs.ui.main.settings;

import ir.reservs.reservs.ui.base.BaseContract;

public class SettingsContract {
    interface View extends BaseContract.View {
        void setUserInfo(String name, String phone, String image);

        void openLoginActivity();
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void logoutUser();
    }
}
