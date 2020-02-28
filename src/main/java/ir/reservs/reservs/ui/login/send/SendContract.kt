package ir.reservs.reservs.ui.login.send

import ir.reservs.reservs.ui.base.BaseFragmentContract

class SendContract {
    interface View : BaseFragmentContract.View {
        fun goToVerifyState(phone:String)

        fun showProgress()

        fun hideProgress()
    }

    interface Presenter : BaseFragmentContract.Presenter<View>
}