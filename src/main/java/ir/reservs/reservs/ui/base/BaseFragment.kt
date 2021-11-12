package ir.reservs.reservs.ui.base

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import ir.reservs.reservs.R
import ir.reservs.reservs.ReserveApplication
import ir.reservs.reservs.di.component.DaggerFragmentComponent
import ir.reservs.reservs.di.component.FragmentComponent
import ir.reservs.reservs.di.module.FragmentModule
import ir.reservs.reservs.ui.custome.StateAdapter
import ir.reservs.reservs.ui.main.MainActivity
import kotlinx.android.synthetic.main.empty_layout.*

abstract class BaseFragment : Fragment(), BaseFragmentContract.View {
    var fragmentComponent: FragmentComponent? = null

    private var stateAdapter: StateAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentComponent = DaggerFragmentComponent.builder()
                .applicationComponent(ReserveApplication.getComponent())
                .fragmentModule(FragmentModule(requireContext()))
                .build()
        setup(view)
    }


    override fun onTokenExpire() {
        Log.e("BaseFragment", "onTokenExpire")
        //clearData
        findNavController().navigate(R.id.sendFragment)
    }


    override fun onError(msg: String?, type: String) {
        (activity as MainActivity).onError(msg!!, type)
    }

    fun initializeStateAdapter(recyclerView: RecyclerView,
                               recyclerAdapter: RecyclerView.Adapter<*>) {
        if (stateAdapter == null) {
            val emptyView = layoutInflater.inflate(R.layout.empty_layout, recyclerView,
                    false)
            val errorView = layoutInflater.inflate(R.layout.error_layout, recyclerView,
                    false)
            val loadingView = layoutInflater.inflate(R.layout.loading_layout, recyclerView,
                    false)
            stateAdapter = StateAdapter(recyclerAdapter, emptyView, loadingView, errorView)
        }
    }

    fun getStateAdapter(): StateAdapter {
        return stateAdapter!!
    }

    override fun errorState() {
        stateAdapter?.currentView = StateAdapter.VIEW_ERROR
    }

    override fun emptyState() {
        stateAdapter?.currentView = StateAdapter.VIEW_EMPTY
    }

    public fun emptyState(title: String, description: String) {
        stateAdapter?.currentView = StateAdapter.VIEW_EMPTY
        txtTitle.text = title
        txtDescription.text = description
    }

    override fun loadingState() {
        getStateAdapter().currentView = StateAdapter.VIEW_LOADING
    }

    override fun normalState() {
        getStateAdapter().currentView = StateAdapter.VIEW_NORMAL
    }


    abstract fun setup(view: View)

    override fun onError(resId: Int) {
        onError(getString(resId))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentComponent = null
        stateAdapter = null
    }

}
