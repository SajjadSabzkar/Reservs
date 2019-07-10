package ir.reservs.reservs.ui.login.login;

import android.util.Log;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import ir.reservs.reservs.R;
import ir.reservs.reservs.data.DataManager;
import ir.reservs.reservs.model.User;
import ir.reservs.reservs.utils.CommonUtils;
import ir.reservs.reservs.utils.RetrofitError;

public class LoginPresenter implements LoginContract.presenter {
    private DataManager dataManager;
    private CompositeDisposable compositeDisposable;
    private LoginContract.View view;

    @Inject
    LoginPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        this.dataManager = dataManager;
        this.compositeDisposable = compositeDisposable;
    }

    private void saveUser(User user) {
        dataManager.setAccessToken(user.getToken());
        dataManager.setCurrentUserName(user.getName());
        dataManager.setCurrentUserPhone(user.getPhone());
//        dataManager.setCurrentUserImage(user.getImage());
    }


    @Override
    public void login(String phone, String password) {
        if (phone == null || phone.isEmpty()) {
            view.onError(R.string.empty_phone);
            return;
        }
        if (!CommonUtils.isPhoneValid(phone)) {
            view.onError(R.string.invalid_phone);
            return;
        }
        if (password == null || password.isEmpty()) {
            view.onError(R.string.empty_password);
            return;
        }
        Log.e("LoginPresenter", "onLogin" + ": " + phone + "," + password);
        view.showProgress();
        compositeDisposable.add(dataManager
                .login(phone, password)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(user -> {
                    Log.e("LoginPresenter", "onLogin" + ": " + user.getName());
                    saveUser(user);
                    view.hideProgress();
                    view.openMainActivity();
                }, error -> {
                    RetrofitError.INSTANCE.handle(view, error);
                    view.hideProgress();
                    Log.e("LoginPresenter", "onLogin" + ": " + error.getMessage());
                }));
    }

    @Override
    public void onAttach(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void onDetach() {
        view = null;
        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }
}
