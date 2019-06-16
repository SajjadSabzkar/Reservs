package ir.reservs.reservs.ui.main.information;

import android.util.Log;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import ir.reservs.reservs.R;
import ir.reservs.reservs.data.DataManager;

public class InformationPresenter implements InformationContract.Presenter {
    private DataManager dataManager;
    private CompositeDisposable compositeDisposable;
    private InformationContract.View view;

    @Inject
    public InformationPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        this.dataManager = dataManager;
        this.compositeDisposable = compositeDisposable;
    }


    @Override
    public void onAttach(InformationContract.View view) {
        this.view = view;
    }

    @Override
    public void onDetach() {
        if (!compositeDisposable.isDisposed())
            compositeDisposable.clear();

        view = null;
    }

    @Override
    public void confirmInformation(String name) {
        if (name.length() == 0) {
            view.onError(R.string.input_your_name);
        }
        compositeDisposable.add(
                dataManager.updateName(name)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                success -> {
                                    dataManager.setCurrentUserName(name);
                                    view.nameUpdated(name);
                                },
                                error -> view.onError(error.getMessage())
                        )
        );
    }
}
