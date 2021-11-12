package ir.reservs.reservs.ui.main.splash

import ir.reservs.reservs.ui.base.BaseFragmentContract

class SplashContract {
    interface View : BaseFragmentContract.View {
        fun goToAuthentication()
        fun goToHome()
        fun internetNotAvailable()
    }

    interface Presenter : BaseFragmentContract.Presenter<View> {

    }
}