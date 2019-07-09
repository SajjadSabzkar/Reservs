package ir.reservs.reservs.ui.main.salons;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ir.reservs.reservs.data.DataManager;
import ir.reservs.reservs.utils.RetrofitError;

public class SalonListPresenter implements SalonListContract.Presenter {

    private SalonListContract.View view;
    private DataManager dataManager;
    private CompositeDisposable compositeDisposable;

    @Inject
    public SalonListPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        this.dataManager = dataManager;
        this.compositeDisposable = compositeDisposable;
    }

    @Override
    public void onAttach(SalonListContract.View view) {
        this.view = view;
        getSalonsFromServer();
    }

    private void getSalonsFromServer() {
        view.loadingState();
        Disposable disposable = dataManager.salons()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        salons -> {
                            if (salons.size() > 0) {
                                view.setSalonsData(salons);
                                view.normalState();
                            } else {
                                view.emptyState();
                            }
                        },
                        error -> {
                            RetrofitError.INSTANCE.handle(view, error);
                            view.errorState();
                        }
                );
        compositeDisposable.add(disposable);
    }

    @Override
    public void onDetach() {
        view = null;
        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.clear();
        }
    }
}
