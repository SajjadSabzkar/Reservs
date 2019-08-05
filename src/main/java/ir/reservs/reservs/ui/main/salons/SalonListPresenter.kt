package ir.reservs.reservs.ui.main.salons

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ir.reservs.reservs.data.DataManager
import ir.reservs.reservs.utils.RetrofitError

class SalonListPresenter(private val dataManager: DataManager, private val compositeDisposable: CompositeDisposable) : SalonListContract.Presenter {

    private var view: SalonListContract.View? = null

    override fun onAttach(view: SalonListContract.View) {
        this.view = view
        getSalonsFromServer()
    }

    private fun getSalonsFromServer() {
        view?.loadingState()
        val disposable = dataManager.salons()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { salons ->
                            if (salons.size > 0) {
                                view!!.setSalonsData(salons)
                                view!!.normalState()
                            } else {
                                view!!.emptyState()
                            }
                        },
                        { error ->
                            RetrofitError.handle(view!!, error)
                            view!!.errorState()
                        }
                )
        compositeDisposable.add(disposable)
    }

    override fun onDetach() {
        view = null
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.clear()
        }
    }
}
