package ir.reservs.reservs.ui.main.verify

import android.util.Log
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
        Log.e("user:", user.toString())
        dataManager.setAccessToken(user.token)
        dataManager.setCurrentUserName(user.name)
        dataManager.setCurrentUserPhone(user.phone)
        dataManager.setIsVerify(user.is_verify)
        dataManager.setCredit(user.credit)
        user.birthday?.let { dataManager.setUserBirthday(it) }
        user.image.let { dataManager.setCurrentUserImage(it) }
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
                    Log.e("user", user.toString())
                    if (user.is_verify)
                        view?.goToMyReserves()
                    else
                        view?.goToEditInfo()
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