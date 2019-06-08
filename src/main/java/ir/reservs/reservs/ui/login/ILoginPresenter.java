package ir.reservs.reservs.ui.login;

import ir.reservs.reservs.ui.base.IPresenter;

public interface ILoginPresenter<V extends ILoginView> extends IPresenter<V> {
    void onLogin(String phone, String password);
}
