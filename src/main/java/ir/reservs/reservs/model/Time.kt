package ir.reservs.reservs.model;

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Time(var id: String, var start: String, var end: String, var price: String) : Parcelable
