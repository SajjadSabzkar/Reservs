package ir.reservs.reservs.ui.main.password;

import ir.reservs.reservs.ui.base.BaseFragmentContract;

class PasswordContract {
    interface View : BaseFragmentContract.View {
        fun passwordChangedSuccessful()
    }

    interface Presenter : BaseFragmentContract.Presenter<View> {
        fun changePassword(current_password: String, new_password: String)
    }
}