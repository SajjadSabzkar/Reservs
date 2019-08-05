package ir.reservs.reservs.ui.main.history

import androidx.lifecycle.LifecycleOwner
import androidx.paging.PagedList
import ir.reservs.reservs.model.History
import ir.reservs.reservs.ui.base.BaseFragmentContract

class HistoryContract {

    interface View : BaseFragmentContract.View, LifecycleOwner {
        fun updateAdapter(it: PagedList<History>)
    }

    interface Presenter : BaseFragmentContract.Presenter<View>


}