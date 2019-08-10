package ir.reservs.reservs.model

import android.os.Parcelable
import android.util.Log
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Day(val date: String, val name: String) : Parcelable {
    fun equals(other: Day): Boolean {
        if (other.name == name && other.date == date) {
            return true
        }
        return false
    }

    fun getNum(): String {
        return date.split("-")[2]
    }
}