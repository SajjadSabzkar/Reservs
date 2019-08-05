package ir.reservs.reservs.data

import android.util.Log
import androidx.paging.DataSource
import io.reactivex.disposables.CompositeDisposable
import ir.reservs.reservs.model.History

class HistoryDataSourceFactory(val dataManager: AppDataManager, val compositeDisposable: CompositeDisposable)
    : DataSource.Factory<Int, History>() {

    override fun create(): HistoryDataSource {
        return HistoryDataSource(dataManager, compositeDisposable)
    }

}