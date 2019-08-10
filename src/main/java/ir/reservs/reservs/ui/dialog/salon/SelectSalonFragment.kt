package ir.reservs.reservs.ui.dialog.salon

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.reservs.reservs.R
import ir.reservs.reservs.model.Salon
import ir.reservs.reservs.ui.base.BaseDialogFragment
import ir.reservs.reservs.ui.main.salons.SalonListAdapter
import ir.reservs.reservs.ui.main.salons.SalonOnClickListener
import kotlinx.android.synthetic.main.select_city_layout.*
import javax.inject.Inject

class SelectSalonFragment(var listener: SalonOnClickListener?, val cityId: Int)
    : BaseDialogFragment(), SelectSalonContract.View, SalonOnClickListener {

    var selectCityPresenter: SelectSalonPresenter? = null
        @Inject set

    var selectCityAdapter: SalonListAdapter? = null
        @Inject set



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
        selectCityPresenter?.fetchSalons(cityId)
    }

    override fun setList(salons: MutableList<Salon>) {
        Log.e("SelectSalonFragment", "setList: " + salons.size)
        selectCityAdapter?.addData(salons)
        normalState()
    }

    override fun onClick(salon: Salon) {
        listener?.onClick(salon)
        dismiss()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        selectCityPresenter?.onDetach()
        listener = null
        selectCityAdapter = null
    }
}