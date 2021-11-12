package ir.reservs.reservs.ui.main.search

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import ir.reservs.reservs.R
import ir.reservs.reservs.model.City
import ir.reservs.reservs.model.Salon
import ir.reservs.reservs.ui.base.BaseFragment
import ir.reservs.reservs.ui.dialog.city.SelectCityFragment
import ir.reservs.reservs.ui.dialog.city.SelectCityFragment.SelectCityListener
import ir.reservs.reservs.ui.dialog.salon.SelectSalonFragment
import ir.reservs.reservs.ui.dialog.salon.SelectSalonFragment.SelectSalonListener
import kotlinx.android.synthetic.main.layout_search.*
import javax.inject.Inject

class SearchFragment : BaseFragment(), SearchContract.View,
        SelectSalonListener, SelectCityListener {

    var searchPresenter: SearchPresenter? = null
        @Inject set

    var dialog: AlertDialog? = null

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

        dialog = AlertDialog.Builder(requireContext())
                .setView(View.inflate(context, R.layout.dialog_error, null))
                .setPositiveButton(R.string.ok_btn) { dialogInterface: DialogInterface, _: Int ->
                    dialogInterface.dismiss()
                }.create()
    }

    override fun openCityDialog() {
        SelectCityFragment(this).show(getFManager(), "")
    }

    override fun openSalonDialog(cityId: Int) {
        SelectSalonFragment(this, cityId).show(getFManager(), "")
    }

    override fun displayDate(date: String) {
        txtDate.text = date
    }

    override fun displayCity(city: City) {
        txtCityName.text = city.name
    }

    override fun onSelect(salon: Salon) {
        txtLocation.text = salon.title
        searchPresenter?.setSalon(salon)
    }

    override fun onSelect(city: City) {
        txtCityName.text = city.name
        searchPresenter?.setCity(city)
    }

    override fun onError() {
        dialog?.show()
    }

    override fun getFManager(): FragmentManager {
        return childFragmentManager;
    }

    override fun goToTimesFragment(salon: Salon, date: String) {
        val b = Bundle()
        b.putParcelable("salon", salon)
        b.putString("date", date)
        findNavController().navigate(R.id.fromSearchToTimes, b)
    }

    override fun onDestroy() {
        searchPresenter?.onDetach()
        searchPresenter = null
        dialog?.dismiss()
        dialog = null
        super.onDestroy()
    }

}
