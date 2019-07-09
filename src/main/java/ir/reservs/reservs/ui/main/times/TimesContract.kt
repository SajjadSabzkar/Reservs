package ir.reservs.reservs.ui.main.times

import ir.reservs.reservs.model.Day
import ir.reservs.reservs.model.Time
import ir.reservs.reservs.ui.base.BaseFragmentContract

class TimesContract {
    interface View : BaseFragmentContract.View {
        fun updateTimes(times: MutableList<Time>)
        fun initializeViews(days: MutableList<Day>, selectedDay: Day)
        fun changeSelectedDay(day: Day)
        fun clearOldTimes()
    }

    interface Presenter : BaseFragmentContract.Presenter<View> {

    }
}