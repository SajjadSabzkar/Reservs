package ir.reservs.reservs.ui.main.splash

import io.reactivex.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ir.reservs.reservs.data.DataManager
import ir.reservs.reservs.ui.base.BaseFragmentContract
import ir.reservs.reservs.utils.RetrofitError

class SplashPresenter(val dataManager: DataManager, val compositeDisposable: CompositeDisposable) : SplashContract.Presenter {
    private var view: SplashContract.View? = null
    private fun decideNextActivity() {
        val isLogin = !dataManager.getAccessToken().isNullOrBlank()
        if (isLogin) {
            view?.goToHome()
        } else {
            view?.goToAuthentication()
        }
    }

    fun sendDeviceInfo(uniqueId: String, push_token: String?, app_version: String, api_level: String,
                       operator: String, brand: String, model: String) {
        val composite = dataManager
                .storeDevice(uniqueId, push_token, app_version, api_level, operator, brand, model)
                .observeOn(mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    decideNextActivity()
                }, { error ->
                    view?.internetNotAvailable()
                    RetrofitError.handle(view as BaseFragmentContract.View, error)
                })
        compositeDisposable.add(composite)
    }

    override fun onAttach(view: SplashContract.View) {
        this.view = view
    }


    override fun onDetach() {
        view = null
        if (!compositeDisposable.isDisposed)
            compositeDisposable.dispose()
    }
}