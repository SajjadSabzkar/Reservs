package ir.reservs.reservs.ui.main.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import ir.reservs.reservs.R
import ir.reservs.reservs.model.History
import ir.reservs.reservs.ui.base.BaseFragment
import ir.reservs.reservs.ui.custome.StateAdapter
import javax.inject.Inject

class HistoryFragment : BaseFragment(), HistoryContract.View {
    var historyAdapter: HistoryAdapter? = null
        @Inject set

    var historyPresenter: HistoryPresenter? = null
        @Inject set

    private var historyRecycler: RecyclerView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_history, container, false)
    }

    override fun setup(view: View) {
        fragmentComponent?.inject(this)
        historyRecycler = view.findViewById(R.id.historyRecycler)
        historyPresenter?.onAttach(this)
        initializeStateAdapter(historyRecycler!!, historyAdapter as RecyclerView.Adapter<*>)
        getStateAdapter().currentView = StateAdapter.VIEW_EMPTY
        historyRecycler?.adapter = getStateAdapter()
        loadingState()
    }

    override fun updateAdapter(it: PagedList<History>) {
        historyAdapter?.submitList(it)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        historyPresenter?.onDetach()
        historyPresenter = null
        historyAdapter = null
        historyRecycler = null
    }

}
