package ir.reservs.reservs.ui.main;

import io.reactivex.disposables.CompositeDisposable;
import ir.reservs.reservs.data.DataManager;
import ir.reservs.reservs.ui.base.BasePresenter;

public class MainPresenter implements BasePresenter<MainContract.View> {
    private DataManager dataManager;
    private CompositeDisposable compositeDisposable;

    MainPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {

        this.dataManager = dataManager;
        this.compositeDisposable = compositeDisposable;
    }

    @Override
    public void onAttach(MainContract.View view) {

    }

    @Override
    public void onDetach() {
        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }

    }
}
