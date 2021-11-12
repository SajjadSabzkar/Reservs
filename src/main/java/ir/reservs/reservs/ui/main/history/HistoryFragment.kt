package ir.reservs.reservs.ui.main.history

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.reservs.reservs.R
import ir.reservs.reservs.model.History
import ir.reservs.reservs.ui.base.BaseFragment
import ir.reservs.reservs.ui.custome.StateAdapter
import ir.reservs.reservs.ui.main.MainActivity
import ir.reservs.reservs.utils.PaginationScrollListener
import kotlinx.android.synthetic.main.layout_history.*
import javax.inject.Inject

class HistoryFragment : BaseFragment(), HistoryContract.View {
    private var isScrolling = false
    var historyAdapter: HistoryAdapter? = null
        @Inject set

    var historyPresenter: HistoryPresenter? = null
        @Inject set


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_history, container, false)
    }

    override fun setup(view: View) {
        fragmentComponent?.inject(this)
        historyPresenter?.onAttach(this)
        (activity as MainActivity).showBottomNavigation()
        initializeStateAdapter(historyRecycler, historyAdapter as RecyclerView.Adapter<*>)
        getStateAdapter().currentView = StateAdapter.VIEW_EMPTY
        historyRecycler.adapter = getStateAdapter()
        loadingState()
        val llMan = LinearLayoutManager(context)
        historyRecycler.layoutManager = llMan
        historyRecycler.addOnScrollListener(object : PaginationScrollListener(llMan) {
            override fun loadMoreItems() {
                Log.e("loadMoreItems", "fetchHistories called here");
                historyPresenter?.fetchHistories()
            }

            override fun isLastPage(): Boolean {
                Log.e("isLastPage", "isLastPage called")
                return historyPresenter?.isLastPage() ?: false
            }

            override fun isLoading(): Boolean {
                Log.e("isLoading", "isLoading is called")
                return historyPresenter?.isLoading() ?: false
            }

        })
    }

    override fun updateAdapter(it: MutableList<History>) {
        historyAdapter?.addHistory(it)
    }

    override fun onDestroyView() {
        historyPresenter?.onDetach()
        historyPresenter = null
        historyAdapter = null
        super.onDestroyView()
    }

}
