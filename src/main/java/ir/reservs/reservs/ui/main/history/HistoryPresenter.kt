package ir.reservs.reservs.ui.main.history

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ir.reservs.reservs.data.DataManager
import ir.reservs.reservs.utils.RetrofitError

class HistoryPresenter(private val dataManager: DataManager,
                       private val compositeDisposable: CompositeDisposable) : HistoryContract.Presenter {

    private var view: HistoryContract.View? = null

    override fun getDataHistory() {
        view!!.loadingState()
        val disposable = dataManager.reserves()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { reserves ->
                            view!!.normalState()
                            view!!.setHistoryData(reserves)
                        },
                        { error ->
                            RetrofitError.handle(view!!, error)
                            view!!.errorState()
                        }
                )
        compositeDisposable.add(disposable)
    }

    override fun onAttach(view: HistoryContract.View) {
        this.view = view

    }

    override fun onDetach() {
        view = null
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.clear()
        }
    }
}
