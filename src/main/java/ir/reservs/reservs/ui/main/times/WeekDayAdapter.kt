package ir.reservs.reservs.ui.main.times

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ir.reservs.reservs.R
import ir.reservs.reservs.model.Day

class WeekDayAdapter(private val days: MutableList<Day>) : RecyclerView.Adapter<WeekDayAdapter.ViewHolder>() {
    var selectedDay: Day? = null
    var listener: OnClickListener? = null

    interface OnClickListener {
        fun onClick(day: Day)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.week_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtDayName.text = days[position].name
        holder.txtDate.text = days[position].getNum()
        if (days[position].equals(selectedDay!!)) {
            holder.txtDate = textSelectStyle(holder.txtDate)
        } else {
            holder.txtDate = textNormalStyle(holder.txtDate)
        }
        holder.txtDate.setOnClickListener {
            listener?.onClick(days[position])
        }
    }

    private fun textSelectStyle(txt: TextView): TextView {
        txt.setBackgroundResource(R.drawable.square_selected_bg)
        txt.setTextColor(Color.WHITE)
        return txt
    }

    private fun textNormalStyle(txt: TextView): TextView {
        txt.setBackgroundResource(R.drawable.square_bg)
        txt.setTextColor(Color.GRAY)
        return txt
    }

    override fun getItemCount(): Int {
        return days.size
    }


    fun setDays(days: List<Day>) {
        this.days.clear()
        this.days.addAll(days)
        notifyDataSetChanged()
    }

    fun selectDay(day: Day) {
        this.selectedDay = day
        notifyDataSetChanged()
        //notifyItemChanged(days.indexOf(selectedDay));
    }
    fun clearAll() {
        this.days.clear()
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtDayName: TextView = itemView.findViewById(R.id.txtDayName)
        var txtDate: TextView = itemView.findViewById(R.id.txtDate)

    }
}
