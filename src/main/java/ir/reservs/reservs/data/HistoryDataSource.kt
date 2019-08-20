package ir.reservs.reservs.data

import android.util.Log
import androidx.paging.PageKeyedDataSource
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import ir.reservs.reservs.model.History

class HistoryDataSource(val dataManager: AppDataManager, val compositeDisposable: CompositeDisposable)
    : PageKeyedDataSource<Int, History>() {

    private val stateSubject = PublishSubject.create<Int>()
    val stateObservable: Observable<Int> = stateSubject

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, History>) {
        val d = dataManager.reserves(1)
                .subscribe { list ->
                    if (list.size > 0) {
                        //   stateSubject.onNext(1)
                    } else {
                        // stateSubject.onNext(0)
                    }
                    callback.onResult(list, null, 2)
                }
        compositeDisposable.add(d)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, History>) {
        stateSubject.onNext(1)
        stateSubject.onComplete()
        val d = dataManager.reserves(params.key)
                .subscribe { it ->
                    callback.onResult(it, params.key + 1)
                }
        compositeDisposable.add(d)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, History>) {
        Log.e("HistoryDataSource", "loadBefore: ")
    }

}