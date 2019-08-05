package ir.reservs.reservs.ui.dialog.city

import io.reactivex.disposables.CompositeDisposable
import ir.reservs.reservs.data.DataManager
import ir.reservs.reservs.utils.RetrofitError

class SelectCityPresenter(val dataManager: DataManager, val compositeDisposable: CompositeDisposable)
    : SelectCityContract.Presenter {

    var view: SelectCityContract.View? = null

    override fun onAttach(view: SelectCityContract.View) {
        this.view = view
        initialize()
    }

    private fun initialize() {
        view?.loadingState()
        val d = dataManager.cities().subscribe({
            view?.normalState()
            view?.setList(it)
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