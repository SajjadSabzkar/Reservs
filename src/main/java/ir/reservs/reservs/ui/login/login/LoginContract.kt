package ir.reservs.reservs.ui.login.login

import ir.reservs.reservs.ui.base.BaseFragmentContract

class LoginContract {
    interface View : BaseFragmentContract.View {
        fun openMainActivity()

        fun showProgress()

        fun hideProgress()
    }

    interface Presenter : BaseFragmentContract.Presenter<View> {
        fun login(phone: String, password: String, fcmToken: String)
    }
}