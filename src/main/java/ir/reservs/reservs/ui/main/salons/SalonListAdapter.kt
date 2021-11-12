package ir.reservs.reservs.ui.main.salons

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ir.reservs.reservs.R
import ir.reservs.reservs.model.Salon
import ir.reservs.reservs.utils.CommonUtils.moneyFormat
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
        holder.imgThumbnail.clipToOutline = true
        holder.salonCard.setOnClickListener { listener?.onClick(mData[position]) }
        if (mData[position].thumbnail.isEmpty()) {
            holder.imgThumbnail.setImageResource(R.drawable.rectangle)
        } else {
            Picasso.get()
                    .load(mData[position].thumbnail)
                    .resize(136, 136)
                    .into(holder.imgThumbnail)
        }

        holder.txtLocation.text = mData[position].title
        holder.txtCity.text = mData[position].cityName
        val minPrice = mData[position].minPrice.toString()
        val maxPrice = mData[position].maxPrice.toString()
        holder.txtPrice.text = moneyFormat(maxPrice)
        holder.txtBasePrice.text = moneyFormat(minPrice)

        holder.btnToGoReserve.setOnClickListener { listener?.onClick(mData[position]) }
    }

    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        var salonCard: ConstraintLayout = view.salonCard
        var imgThumbnail: ImageView = view.imgThumbnail
        var txtLocation: TextView = view.txtLocation
        var txtCity: TextView = view.txtCityName
        var txtPrice: TextView = view.txtPrice
        var txtBasePrice: TextView = view.txtBasePrice
        var txtBasePriceLable: TextView = view.txtBasePriceLabel
        var imgRange: ImageView = view.imgRangePrice
        var btnToGoReserve = view.btnToGoReserve
    }

}