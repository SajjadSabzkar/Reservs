package ir.reservs.reservs.ui.main.search

import androidx.fragment.app.FragmentManager
import ir.reservs.reservs.model.City
import ir.reservs.reservs.model.Salon
import ir.reservs.reservs.ui.base.BaseFragmentContract

class SearchContract {
    interface View : BaseFragmentContract.View {
        fun setDate(date: String)

        fun setCity(city: City)

        fun getFManager(): FragmentManager

        fun openSalonDialog(cityId: Int)

        fun openCityDialog()

        fun goToTimesFragment(salon: Salon, date: String)
    }

    interface Presenter : BaseFragmentContract.Presenter<View>
}