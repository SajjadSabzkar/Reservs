package ir.reservs.reservs.ui.main.results;

import ir.reservs.reservs.ui.base.BaseFragmentContract

class ResultContract {
    interface View : BaseFragmentContract.View {
        fun showProgress()
        fun hideProgress()
        fun initializeViews(state: Boolean)
    }

    interface Presenter : BaseFragmentContract.Presenter<View>
}