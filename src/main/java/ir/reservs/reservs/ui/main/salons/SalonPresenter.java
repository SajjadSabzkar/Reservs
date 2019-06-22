package ir.reservs.reservs.ui.main.salons;

import android.util.Log;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ir.reservs.reservs.data.DataManager;

public class SalonPresenter implements SalonContract.Presenter {

    private SalonContract.View view;
    private DataManager dataManager;
    private CompositeDisposable compositeDisposable;

    @Inject
    public SalonPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        this.dataManager = dataManager;
        this.compositeDisposable = compositeDisposable;
    }

    @Override
    public void onAttach(SalonContract.View view) {
        this.view = view;
        getSalonsFromServer();
    }

    private void getSalonsFromServer() {
        Disposable disposable = dataManager.salons()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        salons -> {
                            view.setSalonsData(salons);
                        },
                        error -> view.onError(error.getMessage())
                );
        compositeDisposable.add(disposable);

    }

    @Override
    public void onDetach() {
        view = null;
        if (!compositeDisposable.isDisposed()) {
            Log.e("SalonPresenter", "onDetach" + ": composite clear");
            compositeDisposable.clear();
        }
    }
}
