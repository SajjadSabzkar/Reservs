package ir.reservs.reservs.ui.login;

import ir.reservs.reservs.ui.base.BaseContract;

public class LoginContract {
    interface View extends BaseContract.BaseView {
        void openMainActivity();

        void showProgress();

        void hideProgress();
    }

    interface presenter extends BaseContract.BasePresenter<View> {
        void login(String phone, String password);
    }
}
