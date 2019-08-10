package ir.reservs.reservs.ui.main.salons

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ir.reservs.reservs.R
import ir.reservs.reservs.model.Salon
import kotlinx.android.synthetic.main.item_salon.view.*

class SalonListAdapter(private val mData: MutableList<Salon>) : RecyclerView.Adapter<SalonListAdapter.Holder>() {
    var listener: SalonOnClickListener? = null

    fun addData(data: MutableList<Salon>) {
        mData.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_salon, parent, false)

        return Holder(view)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.salonCard.setOnClickListener {
            listener?.onClick(mData[position])
        }
        Picasso.get()
                .load(mData[position].thumbnail)
                .resize(136, 136)
                .error(R.drawable.avatar)
                .into(holder.imgThumbnail)

        holder.txtLocation.text = mData[position].title
        holder.txtCity.text = mData[position].cityName
        holder.txtPrice.text = String.format("%s < %s",
                mData[position].minPrice,
                mData[position].maxPrice)
    }

    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        var salonCard: CardView = view.salonCard
        var imgThumbnail: ImageView = view.imgThumbnail
        var txtLocation: TextView = view.txtLocation
        var txtCity: TextView = view.txtCityName
        var txtPrice: TextView = view.txtPrice

    }

}