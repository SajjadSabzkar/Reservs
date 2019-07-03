package ir.reservs.reservs.ui.login.register

import ir.reservs.reservs.ui.base.BaseContract

class RegisterContract {
    interface View : BaseContract.View {
        fun openMainActivity()

        fun showProgress()

        fun hideProgress()
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun register(name: String, phone: String, password: String)
    }
}