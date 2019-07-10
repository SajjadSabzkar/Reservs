package ir.reservs.reservs.ui.main.information

import ir.reservs.reservs.ui.base.BaseFragmentContract

class InformationContract {
    interface View : BaseFragmentContract.View {
        fun nameUpdated(name: String)

        fun initializeViews(name: String)
    }

    interface Presenter : BaseFragmentContract.Presenter<View> {
        fun confirmInformation(name: String)

        fun initializeViews()
    }
}