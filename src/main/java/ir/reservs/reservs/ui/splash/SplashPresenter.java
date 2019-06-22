package ir.reservs.reservs.ui.splash;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import ir.reservs.reservs.data.DataManager;

public class SplashPresenter implements SplashContract.Presenter {

    private DataManager dataManager;
    private Disposable disposable;
    private SplashContract.View view;

    @Inject
    public SplashPresenter(DataManager dataManager, CompositeDisposable disposable) {
        this.dataManager = dataManager;
        this.disposable = disposable;
    }


    private void decideNextActivity() {
        Log.e("SplashPresenter","decideNextActivity");
        disposable = Observable.timer(1500, TimeUnit.MILLISECONDS).subscribe(aLong -> {
            if (dataManager.getAccessToken() != null) {
                view.openMainActivity();
            } else {
                view.openLoginActivity();
            }
        });
    }

    @Override
    public void onAttach(SplashContract.View view) {
        this.view = view;
        Log.e("SplashPresenter","onAttach");
        decideNextActivity();
    }

    @Override
    public void onDetach() {
        view = null;
        if (!disposable.isDisposed())
            disposable.dispose();
    }
}
