package ir.reservs.reservs.ui.main.information

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ir.reservs.reservs.R
import ir.reservs.reservs.data.DataManager
import ir.reservs.reservs.utils.RetrofitError

class InformationPresenter(private val dataManager: DataManager,
                           private val compositeDisposable: CompositeDisposable) : InformationContract.Presenter {


    private var view: InformationContract.View? = null

    override fun onAttach(view: InformationContract.View) {
        this.view = view
    }

    override fun onDetach() {
        if (!compositeDisposable.isDisposed)
            compositeDisposable.clear()

        view = null
    }

    override fun confirmInformation(name: String) {
        if (name.isEmpty()) {
            view!!.onError(R.string.input_your_name)
            return
        }
        compositeDisposable.add(
                dataManager.updateName(name)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { (_) ->
                                    dataManager.setCurrentUserName(name)
                                    view!!.nameUpdated(name)
                                },
                                { error -> RetrofitError.handle(view!!, error) }
                        )
        )
    }

    override fun initializeViews() {
        view!!.initializeViews(dataManager.getCurrentUserName())
    }
}
