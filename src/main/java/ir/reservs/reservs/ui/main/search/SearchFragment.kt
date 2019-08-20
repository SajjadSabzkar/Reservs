package ir.reservs.reservs.ui.main.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import ir.reservs.reservs.R
import ir.reservs.reservs.model.City
import ir.reservs.reservs.model.Salon
import ir.reservs.reservs.ui.base.BaseFragment
import ir.reservs.reservs.ui.dialog.city.SelectCityFragment
import ir.reservs.reservs.ui.dialog.salon.SelectSalonFragment
import ir.reservs.reservs.ui.main.salons.SalonOnClickListener
import kotlinx.android.synthetic.main.layout_search.*
import javax.inject.Inject

class SearchFragment : BaseFragment(), SearchContract.View,
        SalonOnClickListener, SelectCityFragment.SelectCityListener {

    var searchPresenter: SearchPresenter? = null
        @Inject set

    var dialog: AlertDialog
    init{
        dialog=AlertDialog.Builder(context!!).
    }
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_search, container, false)
    }

    override fun setup(view: View) {
        fragmentComponent?.inject(this)
        searchPresenter?.onAttach(this)
        constraintLayoutCity.setOnClickListener { searchPresenter?.getCity() }
        constraintLayoutSalon.setOnClickListener { searchPresenter?.getSalon() }
        constraintLayoutDate.setOnClickListener { searchPresenter?.getDate() }
        view.findViewById<Button>(R.id.btnSearch).setOnClickListener { searchPresenter?.search() }
    }

    override fun openCityDialog() {
        SelectCityFragment(this).show(fragmentManager!!, "")
    }

    override fun openSalonDialog(cityId: Int) {
        SelectSalonFragment(this, cityId).show(fragmentManager!!, "")
    }

    override fun displayDate(date: String) {
        txtDate.text = date
    }

    override fun displayCity(city: City) {
        txtCityName.text = city.name
    }

    override fun onClick(salon: Salon) {
        txtLocation.text = salon.title
        searchPresenter?.setSalon(salon)
    }

    override fun onSelect(city: City) {
        txtCityName.text = city.name
        searchPresenter?.setCity(city)
    }

    override fun onError() {

    }

    override fun getFManager(): FragmentManager {
        return fragmentManager!!
    }

    override fun goToTimesFragment(salon: Salon, date: String) {
        val b = Bundle()
        b.putParcelable("salon", salon)
        b.putString("date", date)
        findNavController().navigate(R.id.fromSearchToTimes, b)
    }

    override fun onDestroyView() {
        searchPresenter?.onDetach()
        searchPresenter = null
        super.onDestroyView()
    }

}
