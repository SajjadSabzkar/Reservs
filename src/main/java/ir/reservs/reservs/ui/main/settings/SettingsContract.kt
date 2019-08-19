package ir.reservs.reservs.ui.main.settings;

import ir.reservs.reservs.ui.base.BaseFragmentContract;

class SettingsContract {
    interface View : BaseFragmentContract.View {
        fun setUserInfo(name: String, phone: String)
        fun setUserImage(image: String)
        fun openLoginActivity()
        fun showProgress()
        fun hideProgress()
    }

    interface Presenter : BaseFragmentContract.Presenter<View> {
        fun logoutUser()
    }
}
