package ir.reservs.reservs.ui.main.salons

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
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

    private var progressBar: ProgressBar? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(ir.reservs.reservs.R.layout.fragment_salon_list, container, false)
    }

    override fun setup(view: View?) {
        salonRecyclerView = view?.findViewById(ir.reservs.reservs.R.id.salonsRecyclerView)
        progressBar = view?.findViewById(ir.reservs.reservs.R.id.progressBar)
        //activityComponent.inject(this)
        fragmentComponent.inject(this)
        //fragmentComponnet.inject(this)
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

    override fun onError(msg: String) {
        Log.e("SalonListFragment", msg)
    }

    override fun onClick(salon: Salon?) {
        val bundle = Bundle()
        bundle.putParcelable("salon", salon)
        findNavController().navigate(ir.reservs.reservs.R.id.salonToTimes, bundle)
    }

    override fun showProgress() {
        progressBar?.visibility = View.VISIBLE
        salonRecyclerView?.visibility = View.GONE
    }

    override fun hideProgress() {
        progressBar?.visibility = View.GONE
        salonRecyclerView?.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        if (salonListPresenter != null) {
            salonListPresenter?.onDetach()
            salonListAdapter?.listener = null
            salonListAdapter = null
            salonRecyclerView = null
            salonListPresenter = null
            progressBar = null
        }
        super.onDestroyView()
    }

}