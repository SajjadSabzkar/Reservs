package ir.reservs.reservs.data

import androidx.paging.DataSource
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import ir.reservs.reservs.model.History

class HistoryDataSourceFactory(val dataManager: AppDataManager, val compositeDisposable: CompositeDisposable)
    : DataSource.Factory<Int, History>() {
    private val hd = HistoryDataSource(dataManager, compositeDisposable)
    val stateObservable: Observable<Int> = hd.stateObservable
    override fun create(): HistoryDataSource {
        return hd
    }

}