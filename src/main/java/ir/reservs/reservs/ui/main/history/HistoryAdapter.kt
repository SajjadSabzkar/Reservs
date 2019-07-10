package ir.reservs.reservs.ui.main.history

import android.util.Log
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
        val history = history[position]
        holder.txtName.text = history.name
        holder.txtTime.text = history.startTime
        holder.txtTime.append(" الی ")
        holder.txtTime.append(history.endTime)
        holder.txtDate.text = CommonUtils.dateFormatStandard(history.date)
        holder.txtLocation.text = history.location
        Log.e("HistoryAdapter", "status: ${history.startTime}->" + history.status);
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