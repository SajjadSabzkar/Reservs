package ir.reservs.reservs.ui.main.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import ir.reservs.reservs.R
import ir.reservs.reservs.model.History
import ir.reservs.reservs.utils.CommonUtils
import ir.reservs.reservs.utils.TimeUtils
import kotlinx.android.synthetic.main.item_history.view.*


class HistoryAdapter(var history: ArrayList<History>)
    : PagedListAdapter<History, HistoryAdapter.Holder>(History.difCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_history, parent, false)
        return Holder(view)

    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val history = getItem(position)
        holder.txtSalonName.text = history?.salonName
        holder.txtTime.text = history?.startTime
        holder.txtTime.append(" الی ")
        holder.txtTime.append(history?.endTime)
        holder.txtDate.text = TimeUtils.dateDisplayFormat(history?.date!!)
        holder.txtPrice.text = CommonUtils.moneyDisplayFormat(history.price)
        when {
            history.status == 1 -> holder.txtState.text = "آینده"
            history.status == 0 -> holder.txtState.text = "حال"
            history.status == -1 -> holder.txtState.text = "گذشته"
        }
        holder.viewState = updateStateView(holder.viewState, history.status);
    }

    private fun updateStateView(view: View, state: Int): View {
        when (state) {
            1 -> view.setBackgroundResource(R.color.colorFuture)
            0 -> view.setBackgroundResource(R.color.colorInProcess)
            -1 -> view.setBackgroundResource(R.color.colorCancel)
        }
        return view
    }


    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val txtSalonName: TextView = view.txtSalonName
        val txtTime: TextView = view.txtTime
        val txtDate: TextView = view.txtDate
        val txtPrice: TextView = view.txtPrice
        val txtState: TextView = view.txtState
        var viewState: View = view.viewState
    }
}