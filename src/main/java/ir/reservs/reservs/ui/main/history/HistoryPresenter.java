package ir.reservs.reservs.ui.main.history;

import android.util.Log;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import ir.reservs.reservs.data.DataManager;
import ir.reservs.reservs.ui.base.BasePresenter;

public class HistoryPresenter<V extends IHistoryView>
        extends BasePresenter<V>
        implements IHistoryPresenter {
    HistoryPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);

    }

    @Override
    public void onAttach(V view) {
        super.onAttach(view);
        getDataHistory();
        Log.e("HistoryPresenter", "onAttach" + ": " + 1);
    }

    @Override
    public void getDataHistory() {
        Log.e("HistoryPresenter", "getDataHistory" + ": " + 2);
        getCompositeDisposable().add(
                getDataManager()
                        .reserves()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(reserves ->getView()
                                .setHistoryData(reserves), error ->getView()
                                .onError(error.getMessage())));

    }

    @Override
    public void onDetach() {
        super.onDetach();
        getCompositeDisposable().dispose();
        mView = null;
        mDataManager = null;
        mCompositeDisposable = null;
    }
}
