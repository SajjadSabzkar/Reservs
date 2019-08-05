package ir.reservs.reservs.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class City(var id: Int, var name: String, var slog: String) : Parcelable