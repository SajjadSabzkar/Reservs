package ir.reservs.reservs.ui.dialog.city

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ir.reservs.reservs.R
import ir.reservs.reservs.model.City
import ir.reservs.reservs.ui.base.BaseDialogFragment
import kotlinx.android.synthetic.main.select_city_layout.*
import javax.inject.Inject

class SelectCityFragment(var listener: SelectCityListener?) : BaseDialogFragment(), SelectCityContract.View, SelectCityAdapter.CityOnClickListener {

    var selectCityPresenter: SelectCityPresenter? = null
        @Inject set

    var selectCityAdapter: SelectCityAdapter? = null
        @Inject set

    interface SelectCityListener {
        fun onError()
        fun onSelect(city: City)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.select_city_layout, container, false)
    }

    override fun onResume() {
        super.onResume()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun setup(view: View) {
        fragmentComponent?.inject(this)
        initializeStateAdapter(cityRecyclerView, selectCityAdapter!!)
        cityRecyclerView.adapter = getStateAdapter()
        selectCityAdapter?.listener = this
        selectCityPresenter?.onAttach(this)
    }

    override fun setList(cities: MutableList<City>) {
        selectCityAdapter?.addAll(cities)
        normalState()
    }

    override fun onClick(city: City) {
        listener?.onSelect(city)
        dismiss()
    }

    override fun errorState() {
        listener?.onError()
        dismiss()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        selectCityPresenter?.onDetach()
        listener = null
        selectCityAdapter = null
    }
}