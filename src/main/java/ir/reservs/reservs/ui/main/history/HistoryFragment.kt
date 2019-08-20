package ir.reservs.reservs.ui.main.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.reservs.reservs.R
import ir.reservs.reservs.model.History
import ir.reservs.reservs.ui.base.BaseFragment
import ir.reservs.reservs.ui.custome.StateAdapter
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
        initializeStateAdapter(historyRecycler, historyAdapter as RecyclerView.Adapter<*>)
        getStateAdapter().currentView = StateAdapter.VIEW_EMPTY
        historyRecycler.adapter = getStateAdapter()
        loadingState()
        val llMan = LinearLayoutManager(context)
        historyRecycler.layoutManager = llMan
        historyRecycler.addOnScrollListener(object : PaginationScrollListener(llMan) {
            override fun loadMoreItems() {
                historyPresenter?.fetchHistories()
            }

            override fun isLastPage(): Boolean {
                return historyPresenter?.isLastPage() ?: false
            }

            override fun isLoading(): Boolean {
                return historyPresenter?.isLoading() ?: false
            }

        })
    }

    override fun updateAdapter(it: MutableList<History>) {
        historyAdapter?.addHistory(it)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        historyPresenter?.onDetach()
        historyPresenter = null
        historyAdapter = null
    }

}
