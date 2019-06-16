package ir.reservs.reservs.ui.main.history;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
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
        compositeDisposable.add(
                dataManager.reserves()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                reserves -> view.setHistoryData(reserves),
                                error -> view.onError(error.getMessage())
                        )
        );

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
            compositeDisposable.clear();
        }
    }
}
