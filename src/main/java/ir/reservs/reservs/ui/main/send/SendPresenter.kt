package ir.reservs.reservs.ui.main.send

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ir.reservs.reservs.R
import ir.reservs.reservs.data.DataManager
import ir.reservs.reservs.ui.base.BaseFragmentContract
import ir.reservs.reservs.utils.CommonUtils
import ir.reservs.reservs.utils.RetrofitError

class SendPresenter(val dataManager: DataManager, val compositeDisposable: CompositeDisposable) : SendContract.Presenter {
    private var view: SendContract.View? = null

    fun sendPhone(phone: String) {
        if (phone.isEmpty()) {
            view?.onError(R.string.empty_phone)
            return
        }
        if (!CommonUtils.isPhoneValid(phone)) {
            view?.onError(R.string.invalid_phone)
            return
        }
        view?.showProgress()
        compositeDisposable.add(dataManager
                .sendPhone(phone)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ message ->
                    view?.hideProgress()
                    view?.goToVerifyState(phone)
                    view?.onError(message.message)
                }, { error ->
                    RetrofitError.handle(view as BaseFragmentContract.View, error)
                    view?.hideProgress()
                }))
    }


    override fun onAttach(view: SendContract.View) {
        this.view = view
    }


    override fun onDetach() {
        view = null
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }
}