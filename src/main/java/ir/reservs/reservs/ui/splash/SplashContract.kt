package ir.reservs.reservs.ui.splash

import ir.reservs.reservs.ui.base.BaseFragmentContract

class SplashContract {
    interface View : BaseFragmentContract.View {
        fun openMainActivity()

        fun openLoginActivity()

    }

    interface Presenter : BaseFragmentContract.Presenter<View> {

    }
}