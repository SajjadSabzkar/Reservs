package ir.reservs.reservs.ui.main.information

import androidx.fragment.app.FragmentManager
import ir.reservs.reservs.ui.base.BaseFragmentContract

class InformationContract {
    interface View : BaseFragmentContract.View {
        fun informationUpdated()
        fun displayDate(date: String)
        fun getFManager(): FragmentManager;
        fun initializeViews(name: String, age: String)
    }

    interface Presenter : BaseFragmentContract.Presenter<View>
}