package ir.reservs.reservs.ui.main.salons

import ir.reservs.reservs.model.Salon
import ir.reservs.reservs.ui.base.BaseFragmentContract

class SalonListContract {

    interface View : BaseFragmentContract.View {
        fun setSalonsData(salons: MutableList<Salon>)
    }

    interface Presenter : BaseFragmentContract.Presenter<View>
}
