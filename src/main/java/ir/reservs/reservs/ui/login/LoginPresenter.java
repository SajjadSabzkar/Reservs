package ir.reservs.reservs.ui.login;

import android.util.Log;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import ir.reservs.reservs.R;
import ir.reservs.reservs.data.DataManager;
import ir.reservs.reservs.ui.base.BasePresenter;
import ir.reservs.reservs.utils.CommonUtils;

public class LoginPresenter<V extends ILoginView>
        extends BasePresenter<V>
        implements ILoginPresenter<V> {

    LoginPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
        if (dataManager.getAccessToken() != null) {
            getView().openMainActivity();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCompositeDisposable.dispose();
        mView = null;
        mDataManager = null;
        mCompositeDisposable = null;

    }

    @Override
    public void onLogin(String phone, String password) {
        if (phone == null || phone.isEmpty()) {
            getView().onError(R.string.empty_phone);
            return;
        }
        if (!CommonUtils.isPhoneValid(phone)) {
            getView().onError(R.string.invalid_phone);
            return;
        }
        if (password == null || password.isEmpty()) {
            getView().onError(R.string.empty_password);
            return;
        }
        Log.e("LoginPresenter", "onLogin" + ": " + phone + "," + password);
        getCompositeDisposable().add(getDataManager()
                .login(phone, password)
                .observeOn(Schedulers.computation())
                .subscribeOn(Schedulers.io())
                .subscribe(token -> {
                    Log.e("LoginPresenter", "onLogin" + ": " + token.getToken());
                    saveToken(token.getToken());
                    getView().openMainActivity();
                }, error -> {
                    getView().onError(R.string.invalid_password);
                    Log.e("LoginPresenter", "onLogin" + ": " + error.getMessage());
                }));
    }

    private void saveToken(String token) {
        getDataManager().setAccessToken(token);
    }


}
