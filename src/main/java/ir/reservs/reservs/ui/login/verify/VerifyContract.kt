package ir.reservs.reservs.ui.login.verify

import ir.reservs.reservs.ui.base.BaseFragmentContract

class VerifyContract {
    interface View : BaseFragmentContract.View {
        fun backToPhoneState(phone: String)

        fun openMainActivity()

        fun showProgress()

        fun hideProgress()
    }

    interface Presenter : BaseFragmentContract.Presenter<VerifyContract.View>
}