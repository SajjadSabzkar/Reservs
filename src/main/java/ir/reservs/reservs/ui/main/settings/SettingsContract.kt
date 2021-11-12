package ir.reservs.reservs.ui.main.settings;

import ir.reservs.reservs.ui.base.BaseFragmentContract;

class SettingsContract {
    interface View : BaseFragmentContract.View {
        fun setUserInfo(name: String, phone: String, credit: Long)
        fun setUserImage(image: String)
        fun openLoginPage()
        fun showProgress()
        fun hideProgress()
    }

    interface Presenter : BaseFragmentContract.Presenter<View> {
        fun logoutUser()
    }
}
