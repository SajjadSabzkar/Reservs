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
        Log.e("InformationPresenter", "onAttach");
    }

    @Override
    public void onDetach() {
        Log.e("InformationPresenter", "onDetach");
        if (!compositeDisposable.isDisposed())
            compositeDisposable.dispose();
        view = null;
    }

    @Override
    public void confirmInformation(String name) {
        Log.e("InformationPresenter", "confirmInformation");
        if (name.length() == 0) {
            view.onError(R.string.input_your_name);
        }
        Log.e("InformationPresenter", "confirmInformation" + ": " + compositeDisposable.toString());
        Log.e("InformationPresenter", "confirmInformation" + ": " + dataManager.toString());
        compositeDisposable.add(
                dataManager.updateName(name)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                success -> {
                                    Log.e("InformationPresenter", "confirmInformation" + 2);
                                    dataManager.setCurrentUserName(name);
                                    view.nameUpdated(name);
                                },
                                error -> {
                                    view.onError(error.getMessage());
                                    Log.e("InformationPresenter", "confirmInformation"
                                            + ":error---");
                                }
                        )
        );
    }
}
