package ir.reservs.reservs.data

import android.util.Log
import androidx.paging.PageKeyedDataSource
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import ir.reservs.reservs.model.Error
import ir.reservs.reservs.model.History

class HistoryDataSource(val dataManager: AppDataManager, val compositeDisposable: CompositeDisposable)
    : PageKeyedDataSource<Int, History>() {
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, History>) {
        val d = dataManager.reserves(1)
                .subscribe({ list ->
                    callback.onResult(list, null, 2)
                }, {
                    Log.e("HistoryDataSource", "loadInitial: Error->" + it.message)
                    //errorState.errorState.postValue(Error(300, "internet connection"))
                })
        compositeDisposable.add(d)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, History>) {
        Log.e("HistoryDataSource", "loadAfter: " + params.key)
        val d = dataManager.reserves(params.key)
                .subscribe({
                    callback.onResult(it, params.key + 1)
                }, {
                   // errorState.postValue(Error(300, "internet connection"))
                })
        compositeDisposable.add(d)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, History>) {
        Log.e("HistoryDataSource", "loadBefore: ")
    }

}