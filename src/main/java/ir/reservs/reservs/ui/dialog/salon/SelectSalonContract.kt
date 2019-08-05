package ir.reservs.reservs.ui.dialog.salon

import ir.reservs.reservs.model.Salon
import ir.reservs.reservs.ui.base.BaseFragmentContract

class SelectSalonContract {
    interface View : BaseFragmentContract.View {
        fun setList(salons: MutableList<Salon>)
    }

    interface Presenter : BaseFragmentContract.Presenter<View>
}