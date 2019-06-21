package ir.reservs.reservs.ui.main.settings;

import ir.reservs.reservs.ui.base.BaseContract;

public class SettingsContract {
    interface View extends BaseContract.BaseView {
        void setUserInfo(String name, String phone, String image);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void logoutUser();
    }
}
