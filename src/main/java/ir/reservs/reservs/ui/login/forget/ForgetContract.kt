package ir.reservs.reservs.ui.login.forget

import ir.reservs.reservs.ui.base.BaseFragmentContract

class ForgetContract {
    interface View : BaseFragmentContract.View {
        fun goToLoginFragment()

        fun showProgress()

        fun hideProgress()
    }

    interface Presenter : BaseFragmentContract.Presenter<View>
}