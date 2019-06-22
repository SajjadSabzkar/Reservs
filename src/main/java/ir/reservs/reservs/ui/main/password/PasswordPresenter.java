package ir.reservs.reservs.ui.main.password;

import android.util.Log;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ir.reservs.reservs.R;
import ir.reservs.reservs.data.DataManager;
import retrofit2.HttpException;

public class PasswordPresenter implements PasswordContract.Presenter {
    private PasswordContract.View view;
    private DataManager dataManager;
    private CompositeDisposable compositeDisposable;

    @Inject
    public PasswordPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        this.dataManager = dataManager;
        this.compositeDisposable = compositeDisposable;
    }

    @Override
    public void onAttach(PasswordContract.View view) {
        this.view = view;
    }

    @Override
    public void onDetach() {
        view = null;
        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.clear();
        }
    }

    @Override
    public void changePassword(String current_password, String new_password) {
        if (current_password.length() < 3) {
            view.onError(R.string.password_wrong);
            return;
        }
        if (new_password.length() < 3) {
            view.onError(R.string.new_password_most_have_3_letter);
            return;
        }
        Disposable disposable = dataManager.updatePassword(current_password, new_password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response -> {
                            dataManager.setAccessToken(response.getToken());
                            view.passwordChangedSuccessful();
                        },
                        error -> handleError(error)
                );
        compositeDisposable.add(disposable);
    }

    private void handleError(Throwable error) {
        try {
            HttpException exception = (HttpException) error;
            Log.e("PasswordPresenter", "handleError" + ": " + exception.code());
            switch (exception.code()) {
                case 404:
                case 403:
                case 405:
                case 500:
                case 503:
            }
        }catch (Throwable e){
            Log.e("PasswordPresenter","handleError"+": "+e.getStackTrace().toString());
            Log.e("PasswordPresenter","handleError"+": "+e.getMessage());
            Log.e("PasswordPresenter","handleError"+": "+e.getLocalizedMessage());
        }
    }
}
