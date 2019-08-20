package ir.reservs.reservs.ui.main.history

import io.reactivex.disposables.CompositeDisposable
import ir.reservs.reservs.data.DataManager

class HistoryPresenter(val dataManager: DataManager,
                       private val compositeDisposable: CompositeDisposable)
    : HistoryContract.Presenter {
    private var page = 1
    private var isLastPage = false
    private var isLoading = false
    private var view: HistoryContract.View? = null
    /*private val hdFactory = HistoryDataSourceFactory(dataManager as AppDataManager, compositeDisposable)
    private val pagedListObservable: Observable<PagedList<History>> by lazy {
        val config: PagedList.Config = PagedList.Config.Builder()
                .setInitialLoadSizeHint(2)
                .setEnablePlaceholders(true)
                .setPageSize(2)
                .build()
        RxPagedListBuilder(hdFactory, config)
                .buildObservable()
    }*/

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
                    view?.normalState()
                    view?.updateAdapter(it)
                }, {
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
