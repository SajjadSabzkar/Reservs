package ir.reservs.reservs.ui.main.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.reservs.reservs.R
import ir.reservs.reservs.model.ReserveHistory
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

    override fun setHistoryData(reserves: MutableList<ReserveHistory>) {
        if (reserves.size == 0) {
            getStateAdapter().currentView = StateAdapter.VIEW_EMPTY
            return
        }
        historyAdapter?.addItems(reserves)
    }

    override fun setup(view: View) {
        fragmentComponent?.inject(this)
        historyRecycler = view.findViewById(R.id.historyRecycler)
        historyPresenter?.onAttach(this)
        initializeStateAdapter(historyRecycler!!, historyAdapter!!)
        getStateAdapter().currentView = StateAdapter.VIEW_EMPTY
        historyRecycler?.adapter = getStateAdapter()
        historyPresenter?.getDataHistory()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        historyPresenter?.onDetach()
        historyPresenter = null
    }

}
