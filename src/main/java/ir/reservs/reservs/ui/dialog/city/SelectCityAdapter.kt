package ir.reservs.reservs.ui.dialog.city

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ir.reservs.reservs.R
import ir.reservs.reservs.model.City
import kotlinx.android.synthetic.main.item_city.view.*

class SelectCityAdapter(private val cities: MutableList<City>) : RecyclerView.Adapter<SelectCityAdapter.Holder>() {
    var listener: CityOnClickListener? = null

    interface CityOnClickListener {
        fun onClick(city: City)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_city, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return cities.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.cityName.text = cities[position].name
        holder.cityName.setOnClickListener {
            listener?.onClick(cities[position])
        }
    }

    fun addAll(cities: MutableList<City>) {
        this.cities.addAll(cities)
        notifyDataSetChanged()
    }

    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val cityName: TextView = view.cityName
    }
}