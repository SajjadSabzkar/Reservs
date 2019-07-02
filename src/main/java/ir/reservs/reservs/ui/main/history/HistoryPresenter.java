package ir.reservs.reservs.ui.main.history;

import android.util.Log;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ir.reservs.reservs.data.DataManager;

public class HistoryPresenter implements HistoryContract.Presenter {

    private DataManager dataManager;
    private CompositeDisposable compositeDisposable;
    private HistoryContract.View view;

    @Inject
    public HistoryPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        this.dataManager = dataManager;
        this.compositeDisposable = compositeDisposable;
    }

    public void getDataHistory() {
        view.showProgress();
        Disposable disposable = dataManager.reserves()
                .subscribeOn(Schedulers.io())
                .doOnError(error -> {
                    Log.e("HistoryPresenter", "getDataHistory" + ": " + error.getMessage());
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        reserves -> {
                            view.setHistoryData(reserves);
                            view.hideProgress();
                        },
                        error -> {
                            view.onError(error.getMessage());
                            view.hideProgress();
                        }
                );
        compositeDisposable.add(disposable);
    }

    @Override
    public void onAttach(HistoryContract.View view) {
        this.view = view;
        getDataHistory();
    }

    @Override
    public void onDetach() {
        view = null;
        if (!compositeDisposable.isDisposed()) {
            Log.e("SalonListPresenter", "onDetach" + ": composite clear");
            compositeDisposable.clear();
        }
    }
}
