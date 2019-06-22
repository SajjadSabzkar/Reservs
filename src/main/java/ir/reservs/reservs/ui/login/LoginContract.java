package ir.reservs.reservs.ui.login;

import ir.reservs.reservs.ui.base.BaseContract;

public class LoginContract {
    interface View extends BaseContract.View {
        void openMainActivity();

        void showProgress();

        void hideProgress();
    }

    interface presenter extends BaseContract.Presenter<View> {
        void login(String phone, String password);
    }
}
