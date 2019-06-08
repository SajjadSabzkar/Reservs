package ir.reservs.reservs.ui.main;

import io.reactivex.disposables.CompositeDisposable;
import ir.reservs.reservs.data.DataManager;
import ir.reservs.reservs.ui.base.BasePresenter;

public class MainPresenter<V extends IMainView>
        extends BasePresenter<V>
        implements IMainPresenter<V> {
    MainPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mView = null;
        mDataManager = null;
        mCompositeDisposable = null;

    }
}
