package ir.reservs.reservs.ui.main.reserve;

import ir.reservs.reservs.model.Salon
import ir.reservs.reservs.model.Time
import ir.reservs.reservs.ui.base.BaseContract;

class ReserveContract {
    interface View : BaseContract.View {
        fun initializeViews(name: String, phone: String, time: String)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun initialize(salon: Salon, time: Time)
    }
}
