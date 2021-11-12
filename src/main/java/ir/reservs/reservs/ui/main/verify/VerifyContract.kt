package ir.reservs.reservs.ui.main.verify

import ir.reservs.reservs.ui.base.BaseFragmentContract

class VerifyContract {
    interface View : BaseFragmentContract.View {
        fun backToPhoneState(phone: String)
        fun goToEditInfo()
        fun goToMyReserves()
        fun showProgress()
        fun hideProgress()
    }

    interface Presenter : BaseFragmentContract.Presenter<View>
}