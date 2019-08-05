package ir.reservs.reservs.ui.main.history

import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ir.reservs.reservs.data.AppDataManager
import ir.reservs.reservs.data.DataManager
import ir.reservs.reservs.data.HistoryDataSourceFactory
import ir.reservs.reservs.model.History

class HistoryPresenter(val dataManager: DataManager,
                       private val compositeDisposable: CompositeDisposable)
    : HistoryContract.Presenter {

    private var view: HistoryContract.View? = null
    private val pagedListObservable: Observable<PagedList<History>> by lazy {
        val config: PagedList.Config = PagedList.Config.Builder()
                .setInitialLoadSizeHint(2)
                .setEnablePlaceholders(true)
                .setPageSize(2)
                .build()
        RxPagedListBuilder(HistoryDataSourceFactory(dataManager as AppDataManager, compositeDisposable), config)
                .buildObservable()
    }

    private fun initialize() {
        val d = pagedListObservable
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (it.size == 0) {
                        view?.emptyState()
                    } else {
                        view?.normalState()
                        view?.updateAdapter(it)
                    }
                }
        compositeDisposable.add(d)

        val d2 = ReactiveNetwork.observeInternetConnectivity()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { isConnect ->
                    if (isConnect) {
                        view?.normalState()
                    } else {
                        view?.errorState()
                    }
                }
        compositeDisposable.add(d2)
    }

    override fun onAttach(view: HistoryContract.View) {
        this.view = view
        initialize()
    }

    override fun onDetach() {
        view = null
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.clear()
        }
    }
}
