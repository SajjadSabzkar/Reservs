package ir.reservs.reservs.ui.main.times

import ir.reservs.reservs.model.Day
import ir.reservs.reservs.model.Time
import ir.reservs.reservs.ui.base.BaseContract

class TimesContract {
    interface View : BaseContract.View {
        fun updateTimes(times: MutableList<Time>)
        fun initializeViews(days: MutableList<Day>, selectedDay: Day)
        fun showProgress()
        fun hideProgress()
        fun changeSelectedDay(day: Day)
    }

    interface Presenter : BaseContract.Presenter<View> {

    }
}