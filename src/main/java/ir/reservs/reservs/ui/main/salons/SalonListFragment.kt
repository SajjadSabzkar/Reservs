package ir.reservs.reservs.ui.main.salons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import ir.reservs.reservs.model.Salon
import ir.reservs.reservs.ui.base.BaseFragment
import javax.inject.Inject


class SalonListFragment : BaseFragment(), SalonListContract.View, SalonOnClickListener {

    var salonListPresenter: SalonListPresenter? = null
        @Inject set

    var salonListAdapter: SalonListAdapter? = null
        @Inject set

    private var salonRecyclerView: RecyclerView? = null

    //private var stateAdapter: StateAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(ir.reservs.reservs.R.layout.fragment_salon_list, container, false)
    }


    override fun setup(view: View) {
        salonRecyclerView = view.findViewById(ir.reservs.reservs.R.id.salonsRecyclerView)
        fragmentComponent?.inject(this)
        salonListAdapter?.listener = this
        initializeStateAdapter(salonRecyclerView!!, salonListAdapter!!)
        salonRecyclerView?.adapter = getStateAdapter()
        salonListPresenter?.onAttach(this)
    }

    override fun setSalonsData(salons: MutableList<Salon>) {
        salonListAdapter?.addData(salons)
        salonListAdapter?.notifyDataSetChanged()
    }

    override fun onClick(salon: Salon) {
        val bundle = Bundle()
        bundle.putParcelable("salon", salon)
        findNavController().navigate(ir.reservs.reservs.R.id.salonToTimes, bundle)
    }

    override fun onDestroyView() {
        if (salonListPresenter != null) {
            salonListPresenter?.onDetach()
            salonListAdapter?.listener = null
            salonListAdapter = null
            salonRecyclerView = null
            salonListPresenter = null
        }
        super.onDestroyView()
    }

}