package ir.reservs.reservs.ui.login;

import ir.reservs.reservs.ui.base.BasePresenter;
import ir.reservs.reservs.ui.base.BaseView;

public class LoginContract {
    interface View extends BaseView {
        void openMainActivity();

        void showProgress();

        void hideProgress();
    }

    interface presenter extends BasePresenter<View> {
        void login(String phone, String password);
    }
}
