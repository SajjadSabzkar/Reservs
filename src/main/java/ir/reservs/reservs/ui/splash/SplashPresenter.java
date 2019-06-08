package ir.reservs.reservs.ui.splash;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import ir.reservs.reservs.data.DataManager;
import ir.reservs.reservs.ui.base.BasePresenter;

public class SplashPresenter<V extends ISplashView>
        extends BasePresenter<V>
        implements ISplashPresenter {

    SplashPresenter(DataManager dataManager,
                    CompositeDisposable compositeDisposable) {
        this.mDataManager = dataManager;
        this.mCompositeDisposable = compositeDisposable;
    }


    @Override
    public void onAttach(V view) {
        super.onAttach(view);
        decideNextActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mView = null;
        mDataManager = null;
        mCompositeDisposable = null;

    }

    private void decideNextActivity() {
        getCompositeDisposable().add(
                Observable.timer(3, TimeUnit.SECONDS).subscribe(aLong -> {
                    if (getDataManager().getAccessToken() != null) {
                        getView().openMainActivity();
                    } else {
                        getView().openLoginActivity();
                    }
                }));
    }

}
