package ir.reservs.reservs.model

import android.os.Parcelable
import android.util.Log
import androidx.recyclerview.widget.DiffUtil
import kotlinx.android.parcel.Parcelize

@Parcelize
data class History(var id: Int,
                   var name: String,
                   var salonName: String,
                   var date: String,
                   var startTime: String,
                   var endTime: String,
                   var price: String,
                   var status: Int) : Parcelable {
    companion object {
        val difCallBack: DiffUtil.ItemCallback<History> = object : DiffUtil.ItemCallback<History>() {
            override fun areItemsTheSame(oldItem: History, newItem: History): Boolean {
                val dateCheck = (oldItem.date == newItem.date)
                val timeCheck = (oldItem.startTime == newItem.startTime)
                return (dateCheck && timeCheck)
            }

            override fun areContentsTheSame(oldItem: History, newItem: History): Boolean {
                return oldItem == newItem
            }
        }
    }
}