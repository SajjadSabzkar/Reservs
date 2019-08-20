package ir.reservs.reservs.ui.login.register

import android.util.Log
import io.reactivex.disposables.CompositeDisposable
import ir.reservs.reservs.data.DataManager
import ir.reservs.reservs.model.User
import ir.reservs.reservs.ui.base.BaseFragmentContract
import ir.reservs.reservs.utils.RetrofitError

class RegisterPresenter(val dataManager: DataManager, val compositeDisposable: CompositeDisposable) : RegisterContract.Presenter {
    var view: RegisterContract.View? = null

    override fun onAttach(view: RegisterContract.View) {
        this.view = view
    }

    override fun register(name: String, phone: String, password: String, fcmToken: String) {
        if (name.length < 3 || name.indexOf(" ") == -1) {
            view?.onError("نام معتبر وارد کنید")
            return
        }
        if (phone.length < 11) {
            view?.onError("تلفن معتبر وارد کنید")
            return
        }
        if (password.length < 5) {
            view?.onError("رمز عبور باید حداقل پنج حرف باشد")
            return
        }
        view?.showProgress()
        val dispose = dataManager.register(name, phone, password, fcmToken)
                .subscribe({ user: User ->
                    saveUser(user)
                    view?.hideProgress()
                    view?.openMainActivity()
                }, {
                    view?.hideProgress()
                    RetrofitError.handle(view as BaseFragmentContract.View, it)
                    if (RetrofitError.code(it) == 422) {
                        view?.onError("تلفن وارد شده معتبر نیست")
                    }
                })
        compositeDisposable.add(dispose)
    }

    private fun saveUser(user: User) {
        dataManager.setAccessToken(user.token)
        dataManager.setCurrentUserName(user.name)
        dataManager.setCurrentUserPhone(user.phone)
//        dataManager.setCurrentUserImage(user.getImage())
    }

    override fun onDetach() {
        view = null
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.clear()
        }
    }

}