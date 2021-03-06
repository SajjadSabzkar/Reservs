package ir.reservs.reservs.ui.main.history

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ir.reservs.reservs.R
import ir.reservs.reservs.model.History
import ir.reservs.reservs.utils.CommonUtils
import ir.reservs.reservs.utils.TimeUtils
import kotlinx.android.synthetic.main.item_history.view.*


class HistoryAdapter(var history: ArrayList<History>) : RecyclerView.Adapter<HistoryAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_history, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = history[position]
        holder.txtSalonName.text = item.salonName
        Log.e("item",item.startTime+","+item.endTime)
        holder.txtTime.text = item.startTime
        holder.txtTime.append(" الی ")
        holder.txtTime.append(item.endTime)
        holder.txtDate.text = TimeUtils.dateDisplayFormat(item.date)
        holder.txtPrice.text = CommonUtils.moneyDisplayFormat(item.price)
        when {
            item.status == 1 -> holder.txtState.text = "آینده"
            item.status == 0 -> holder.txtState.text = "حال"
            item.status == -1 -> holder.txtState.text = "گذشته"
        }
        holder.viewState = updateStateView(holder.viewState, item.status)
    }

    override fun getItemCount(): Int {
        return history.size
    }

    fun addHistory(items: MutableList<History>) {
        history.addAll(items)
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