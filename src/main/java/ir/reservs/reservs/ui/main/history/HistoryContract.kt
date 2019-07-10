package ir.reservs.reservs.ui.main.history

import ir.reservs.reservs.model.ReserveHistory
import ir.reservs.reservs.ui.base.BaseFragmentContract

class HistoryContract {

    interface View : BaseFragmentContract.View {
        fun setHistoryData(reserves: MutableList<ReserveHistory>)
    }

    interface Presenter : BaseFragmentContract.Presenter<View> {
        fun getDataHistory()
    }


}