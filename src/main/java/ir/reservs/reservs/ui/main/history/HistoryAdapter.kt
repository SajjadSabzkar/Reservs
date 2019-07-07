package ir.reservs.reservs.ui.main.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ir.reservs.reservs.R
import ir.reservs.reservs.model.ReserveHistory
import ir.reservs.reservs.utils.CommonUtils
import kotlinx.android.synthetic.main.item_history.view.*

class HistoryAdapter(var history: ArrayList<ReserveHistory>) : RecyclerView.Adapter<HistoryAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_history, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return history.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        //todo  history[position] must not be null
        val reserveHistory = history[position]
        holder.txtName.text = reserveHistory.name
        holder.txtTime.text = reserveHistory.startTime
        holder.txtTime.append(" الی ")
        holder.txtTime.append(reserveHistory.endTime)
        holder.txtDate.text = CommonUtils.dateFormatStandard(reserveHistory.date)
        holder.txtLocation.text = reserveHistory.location
        when {
            reserveHistory.status == 1 -> holder.txtState.text = "آینده"
            reserveHistory.status == 0 -> holder.txtState.text = "حال"
            reserveHistory.status == -1 -> holder.txtState.text = "گذشته"
        }
        holder.viewState = updateStateView(holder.viewState, reserveHistory.status);
    }

    private fun updateStateView(view: View, state: Int): View {
        when (state) {
            1 -> view.setBackgroundResource(R.color.colorFuture)
            2 -> view.setBackgroundResource(R.color.colorInProcess)
            3 -> view.setBackgroundResource(R.color.colorCancel)
        }
        return view
    }

    fun addItems(reserveHistories: List<ReserveHistory>) {
        history.addAll(reserveHistories)
        notifyDataSetChanged()
    }


    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val txtName: TextView = view.txtName
        val txtTime: TextView = view.txtTime
        val txtDate: TextView = view.txtDate
        val txtLocation: TextView = view.txtLocation
        val txtState: TextView = view.txtState
        var viewState: View = view.viewState
    }
}