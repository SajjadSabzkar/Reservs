package ir.reservs.reservs.ui.main.results;

import android.content.Context
import android.net.Uri
import com.zarinpal.ewallets.purchase.ZarinPal
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ir.reservs.reservs.data.DataManager

class ResultPresenter(val dataManager: DataManager, val compositeDisposable: CompositeDisposable) : ResultContract.Presenter {

    private var view: ResultContract.View? = null

    override fun onAttach(view: ResultContract.View) {
        this.view = view
    }

    override fun onReceive(context: Context, data: Uri) {
        view?.showProgress()
        updateServerState(data.authority)
    }

    private fun updateServerState(authority: String?) {
        val disposable = dataManager.reserveUpdate(authority!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view?.initializeViews(true)
                    view?.hideProgress()
                }, {
                    //todo: make loop if lose internet connection
                    updateServerState(authority)
                })
        compositeDisposable.add(disposable)
    }

    override fun onDetach() {
        view = null
        ZarinPal.getPurchase(null)
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.clear()
        }
    }

}
