package ir.reservs.reservs.ui.main.salons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import ir.reservs.reservs.R
import ir.reservs.reservs.model.Salon
import ir.reservs.reservs.ui.base.BaseFragment
import javax.inject.Inject

class SalonListFragment : BaseFragment(), SalonListContract.View, SalonOnClickListener {

    var salonListPresenter: SalonListPresenter? = null
        @Inject set

    var salonListAdapter: SalonListAdapter? = null
        @Inject set

    private var salonRecyclerView: RecyclerView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_salon_list, container, false)
    }

    override fun setup(view: View?) {
        salonRecyclerView = view?.findViewById<RecyclerView>(R.id.salonsRecyclerView)
        activityComponent.inject(this)
        //activityComponent!!.inject(this)
        salonListAdapter?.listener = this
        salonRecyclerView?.adapter = salonListAdapter
        salonListPresenter?.onAttach(this)
    }

    override fun setSalonsData(salons: MutableList<Salon>?) {
        if (salons != null) {
            salonListAdapter?.addData(salons)
            salonListAdapter?.notifyDataSetChanged()
        }
    }

    override fun onError(msg: String?) {

    }

    override fun onClick(salon: Salon?) {
        findNavController().navigate(R.id.salonToTimes)
    }

    override fun onDestroyView() {
        if (salonListPresenter != null) {
            salonListPresenter?.onDetach()
            salonListAdapter?.listener = null
            salonListPresenter = null
        }
        super.onDestroyView()
    }


}