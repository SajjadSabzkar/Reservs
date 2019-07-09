package ir.reservs.reservs.ui.main.history;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ir.reservs.reservs.data.DataManager;
import ir.reservs.reservs.utils.RetrofitError;

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
        view.loadingState();
        Disposable disposable = dataManager.reserves()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        reserves -> {
                            view.normalState();
                            view.setHistoryData(reserves);
                        },
                        error -> {
                            RetrofitError.INSTANCE.handle(view, error);
                            view.errorState();
                        }
                );
        compositeDisposable.add(disposable);
    }

    @Override
    public void onAttach(HistoryContract.View view) {
        this.view = view;

    }

    @Override
    public void onDetach() {
        view = null;
        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.clear();
        }
    }
}
