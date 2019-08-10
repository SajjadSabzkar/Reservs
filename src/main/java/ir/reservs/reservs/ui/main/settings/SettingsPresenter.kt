package ir.reservs.reservs.ui.main.settings;

import ir.reservs.reservs.data.DataManager

class SettingsPresenter(val dataManager: DataManager) : SettingsContract.Presenter {

    private var view: SettingsContract.View? = null

    override fun onAttach(view: SettingsContract.View) {
        this.view = view
    }

    override fun logoutUser() {
        dataManager.removeAccessToken()
        view?.openLoginActivity()
    }

    override fun onDetach() {
        view = null
    }

    fun setUserProfileData() {
        view?.setUserInfo(dataManager.getCurrentUserName(),
                dataManager.getCurrentUserPhone(),
                dataManager.getCurrentUserImage())
    }
}
