package ir.reservs.reservs.ui.splash

import android.util.Log
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import ir.reservs.reservs.data.DataManager
import java.util.concurrent.TimeUnit


class SplashPresenter(private val dataManager: DataManager, private val compositeDisposable: CompositeDisposable) : SplashContract.Presenter {
    private var view: SplashContract.View? = null

    private fun decideNextActivity() {
        val disposable = Observable.timer(1500, TimeUnit.MILLISECONDS).subscribe { _ ->
            if (dataManager.getAccessToken() != "-1") {
                view!!.openMainActivity()
            } else {
                view!!.openLoginActivity()
            }
        }
        compositeDisposable.add(disposable)
    }

    override fun onAttach(view: SplashContract.View) {
        this.view = view
        decideNextActivity()
    }


    override fun onDetach() {
        view = null
        if (!compositeDisposable.isDisposed)
            compositeDisposable.dispose()
    }
}
