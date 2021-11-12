package ir.reservs.reservs.ui.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.RecyclerView
import ir.reservs.reservs.R
import ir.reservs.reservs.ReserveApplication
import ir.reservs.reservs.di.component.DaggerFragmentComponent
import ir.reservs.reservs.di.component.FragmentComponent
import ir.reservs.reservs.di.module.FragmentModule
import ir.reservs.reservs.ui.custome.StateAdapter

abstract class BaseDialogFragment: DialogFragment(), BaseFragmentContract.View  {
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

    abstract fun setup(view: View)



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

    override fun onTokenExpire() {

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

    override fun loadingState() {
        getStateAdapter().currentView = StateAdapter.VIEW_LOADING
    }

    override fun normalState() {
        getStateAdapter().currentView = StateAdapter.VIEW_NORMAL
    }

    override fun onError(resId: Int) {
        onError(getString(resId))
    }


    override fun onDestroyView() {
        super.onDestroyView()
        fragmentComponent = null
        stateAdapter = null
    }
}