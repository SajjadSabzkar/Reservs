package ir.reservs.reservs.ui.login.register

import ir.reservs.reservs.ui.base.BaseFragmentContract

class RegisterContract {
    interface View : BaseFragmentContract.View {
        fun openMainActivity()

        fun showProgress()

        fun hideProgress()
    }

    interface Presenter : BaseFragmentContract.Presenter<View> {
        fun register(name: String, phone: String, password: String)
    }
}