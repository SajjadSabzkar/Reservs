package ir.reservs.reservs.ui.base;

import io.reactivex.disposables.CompositeDisposable;
import ir.reservs.reservs.data.DataManager;

public class BasePresenter<V extends IView> implements IPresenter<V> {
    private static final String TAG = "BasePresenter";
    protected DataManager mDataManager;
    protected CompositeDisposable mCompositeDisposable;
    protected V mView;

    public BasePresenter(DataManager dataManager,
                         CompositeDisposable compositeDisposable) {
        this.mDataManager = dataManager;
        this.mCompositeDisposable = compositeDisposable;
    }

    public BasePresenter(DataManager dataManager) {
        this.mDataManager = dataManager;
    }

    public BasePresenter(
            CompositeDisposable compositeDisposable) {
        this.mCompositeDisposable = compositeDisposable;
    }

    public BasePresenter() {

    }

    @Override
    public void onAttach(V view) {
        this.mView = view;
    }

    @Override
    public void onDetach() {
        mCompositeDisposable.dispose();
        mView = null;
    }

    public DataManager getDataManager() {
        return mDataManager;
    }

    private boolean isViewAttached() {
        return mView != null;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new IViewNotAttachedException();
    }


    protected V getView() {
        return mView;
    }


    protected CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }


    public static class IViewNotAttachedException extends RuntimeException {
        IViewNotAttachedException() {
            super("Please call Presenter.onAttach(IView) before" +
                    " requesting data to the Presenter");
        }
    }
}
