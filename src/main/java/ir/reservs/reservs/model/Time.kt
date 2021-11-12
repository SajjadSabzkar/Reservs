package ir.reservs.reservs.model;

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Time(var id: Int, var start: String, var end: String, var price: Int) : Parcelable
