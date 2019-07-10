package ir.reservs.reservs.ui.main.reserve;

import android.content.Context
import ir.reservs.reservs.model.Day
import ir.reservs.reservs.model.Salon
import ir.reservs.reservs.model.Time
import ir.reservs.reservs.ui.base.BaseFragmentContract;

class ReserveContract {
    interface View : BaseFragmentContract.View {
        fun initializeViews(name: String, phone: String, time: String)
        fun context(): Context?
        fun showProgress()
        fun hideProgress()
    }

    interface Presenter : BaseFragmentContract.Presenter<View> {
        fun initialize(salon: Salon, time: Time, day: Day)
        fun payment()
    }
}
