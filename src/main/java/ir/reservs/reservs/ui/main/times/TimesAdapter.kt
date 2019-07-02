package ir.reservs.reservs.ui.main.times

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ir.reservs.reservs.R
import ir.reservs.reservs.model.Time
import kotlinx.android.synthetic.main.times_item.view.*

class TimesAdapter : RecyclerView.Adapter<TimesAdapter.Holder>() {
    val times: MutableList<Time> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.times_item, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return times.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.txtStart.text = times[position].start
        holder.txtEnd.text = times[position].end
        holder.btnReserve.text = "${times[position].price} تومان "
    }

    fun addTimes(times: MutableList<Time>) {
        this.times.addAll(times)
        notifyDataSetChanged()
    }

    fun clearAll() {
        this.times.clear()
        notifyDataSetChanged()
    }

    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val txtEnd: TextView = view.txtEnd
        val txtStart: TextView = view.txtStart
        val btnReserve: Button = view.btnReserve

        init {
            btnReserve.setOnClickListener { Log.e("TimesAdapter", adapterPosition.toString()) }
        }
    }
}