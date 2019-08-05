package ir.reservs.reservs.ui.dialog.city

import ir.reservs.reservs.model.City
import ir.reservs.reservs.ui.base.BaseFragmentContract

class SelectCityContract {
    interface View : BaseFragmentContract.View {
        fun setList(cities: MutableList<City>)
    }

    interface Presenter : BaseFragmentContract.Presenter<View>
}