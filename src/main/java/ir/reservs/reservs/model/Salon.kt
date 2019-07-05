package ir.reservs.reservs.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Salon(
        var id: Int,
        var title: String,
        var description: String,
        var thumbnail: String,
        var week: Int,
        var minPrice: Int,
        var maxPrice: Int,
        var cityName: String
): Parcelable