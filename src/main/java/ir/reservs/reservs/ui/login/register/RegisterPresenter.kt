package ir.reservs.reservs.ui.login.register

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ir.reservs.reservs.data.DataManager
import ir.reservs.reservs.model.User
import ir.reservs.reservs.ui.base.BaseFragmentContract
import ir.reservs.reservs.utils.RetrofitError

class RegisterPresenter(val dataManager: DataManager, val compositeDisposable: CompositeDisposable) : RegisterContract.Presenter {
    var view: RegisterContract.View? = null

    override fun onAttach(view: RegisterContract.View) {
        this.view = view
    }

    override fun register(name: String, phone: String, password: String) {
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
        Log.e("RegisterPresenter", "checks pass")
        view?.showProgress()
        val dispose = dataManager.register(name, phone, password)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ user: User ->
                    dataManager.currentUserName = user.name
                    dataManager.currentUserPhone = user.phone
                    dataManager.currentUserImage = user.image
                    dataManager.accessToken = user.token
                    view?.hideProgress()
                    view?.openMainActivity()
                }, { error: Throwable ->
                    view?.hideProgress()
                    RetrofitError.handle(view as BaseFragmentContract.View, error)
                    if (RetrofitError.code(error) == 422) {
                        view?.onError("تلفن وارد شده معتبر نیست")
                    }
                })
        compositeDisposable.add(dispose)
    }

    override fun onDetach() {
        view = null
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.clear()
        }
    }

}