package ir.reservs.reservs.ui.login.forget

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ir.reservs.reservs.R
import ir.reservs.reservs.data.DataManager
import ir.reservs.reservs.ui.base.BaseFragmentContract
import ir.reservs.reservs.utils.CommonUtils
import ir.reservs.reservs.utils.RetrofitError

class ForgetPresenter(val dataManager: DataManager, val compositeDisposable: CompositeDisposable) : ForgetContract.Presenter {

    private var view: ForgetContract.View? = null

    fun login(phone: String) {
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
                .forget(phone)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ _ ->
                    view?.hideProgress()
                    view?.onError("کلمه عبور از طریق پیامک برایتان ارسال شد.")
                    view?.goToLoginFragment()
                }, { error ->
                    RetrofitError.handle(view as BaseFragmentContract.View, error)
                    view?.hideProgress()
                }))
    }

    override fun onAttach(view: ForgetContract.View) {
        this.view = view
    }


    override fun onDetach() {
        view = null
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }
}
