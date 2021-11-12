package ir.reservs.reservs.ui.main.history

import io.reactivex.disposables.CompositeDisposable
import ir.reservs.reservs.data.DataManager
import ir.reservs.reservs.utils.RetrofitError

class HistoryPresenter(val dataManager: DataManager,
                       private val compositeDisposable: CompositeDisposable)
    : HistoryContract.Presenter {
    private var page = 1
    private var isLastPage = false
    private var isLoading = false
    private var view: HistoryContract.View? = null

    override fun onAttach(view: HistoryContract.View) {
        this.view = view
        fetchHistories()
    }

    fun fetchHistories() {
        isLoading = true
        val d = dataManager.reserves(page)
                .subscribe({
                    isLoading = false
                    page++
                    view?.updateAdapter(it)
                    if (it.size > 0)
                        view?.normalState()
                    else {
                        if (page == 1)
                            view?.emptyState()
                        else
                            isLastPage = true
                    }
                }, {
                    isLoading = false
                    RetrofitError.handle(view!!, it)
                    view?.errorState()
                })
        compositeDisposable.add(d)
    }

    fun isLastPage(): Boolean {
        return isLastPage
    }

    fun isLoading(): Boolean {
        return isLoading
    }

    override fun onDetach() {
        view = null
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.clear()
        }
    }
}
