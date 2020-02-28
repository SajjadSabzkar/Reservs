package ir.reservs.reservs.ui.login.verify

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ir.reservs.reservs.R
import ir.reservs.reservs.data.DataManager
import ir.reservs.reservs.model.User
import ir.reservs.reservs.ui.base.BaseFragmentContract
import ir.reservs.reservs.utils.RetrofitError

class VerifyPresenter(val dataManager: DataManager, val compositeDisposable: CompositeDisposable) : VerifyContract.Presenter {
    private var view: VerifyContract.View? = null

    private fun saveUser(user: User) {
        dataManager.setAccessToken(user.token)
        dataManager.setCurrentUserName(user.name)
        dataManager.setCurrentUserPhone(user.phone)
        dataManager.setIsVerify(user.is_verify)
        if (user.image.isNotEmpty()) {
            dataManager.setCurrentUserImage(user.image)
        }
    }

    fun confirmCode(phone: String, code: String) {
        if (code.isEmpty()) {
            view?.onError(R.string.empty_confirm_code)
            return
        }
        view?.showProgress()
        compositeDisposable.add(dataManager
                .confirmCode(phone, code)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ user ->
                    saveUser(user)
                    view?.hideProgress()
                    view?.openMainActivity()
                }, { error ->
                    RetrofitError.handle(view as BaseFragmentContract.View, error)
                    view?.hideProgress()
                }))
    }

    override fun onAttach(view: VerifyContract.View) {
        this.view = view
    }

    override fun onDetach() {
        view = null
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }
}