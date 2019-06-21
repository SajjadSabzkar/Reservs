package ir.reservs.reservs.ui.splash;

import ir.reservs.reservs.ui.base.BaseContract;

class SplashContract {
    interface View {
        void openMainActivity();

        void openLoginActivity();

        void onError(String text);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {

    }
}
