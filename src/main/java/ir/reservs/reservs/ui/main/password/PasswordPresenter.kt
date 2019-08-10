package ir.reservs.reservs.ui.main.password

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ir.reservs.reservs.R
import ir.reservs.reservs.data.DataManager
import ir.reservs.reservs.utils.RetrofitError

class PasswordPresenter(val dataManager: DataManager, val compositeDisposable: CompositeDisposable) : PasswordContract.Presenter {
    private var view: PasswordContract.View? = null

    override fun onAttach(view: PasswordContract.View) {
        this.view = view
    }

    override fun onDetach() {
        view = null
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.clear()
        }
    }

    override fun changePassword(current_password: String, new_password: String) {
        if (current_password.length < 3) {
            view?.onError(R.string.password_wrong)
            return
        }
        if (new_password.length < 3) {
            view?.onError(R.string.new_password_most_have_3_letter)
            return
        }
        val disposable = dataManager.updatePassword(current_password, new_password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    dataManager.setAccessToken(it.token)
                    view?.passwordChangedSuccessful()
                }, {
                    RetrofitError.handle(view!!, it)
                })
        compositeDisposable.add(disposable)
    }
}
