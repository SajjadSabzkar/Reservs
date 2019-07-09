package ir.reservs.reservs.ui.login.login;

import ir.reservs.reservs.ui.base.BaseFragmentContract;

public class LoginContract {
    interface View extends BaseFragmentContract.View {
        void openMainActivity();

        void showProgress();

        void hideProgress();
    }

    interface presenter extends BaseFragmentContract.Presenter<View> {
        void login(String phone, String password);
    }
}