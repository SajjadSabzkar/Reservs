package ir.reservs.reservs.ui.dialog.salon

import io.reactivex.disposables.CompositeDisposable
import ir.reservs.reservs.data.DataManager
import ir.reservs.reservs.utils.RetrofitError

class SelectSalonPresenter(val dataManager: DataManager, val compositeDisposable: CompositeDisposable)
    : SelectSalonContract.Presenter {

    var view: SelectSalonContract.View? = null

    override fun onAttach(view: SelectSalonContract.View) {
        this.view = view
    }

    fun fetchSalons(cityId: Int) {
        view?.loadingState()
        val d = dataManager.salons(cityId).subscribe({
            if (it.size == 0) {
                view?.emptyState()
            } else {
                view?.normalState()
                view?.setList(it)
            }
        }, {
            RetrofitError.handle(view!!, it)
            view?.errorState()
        })
        compositeDisposable.add(d)
    }

    override fun onDetach() {
        view = null
        compositeDisposable.dispose()
    }

}