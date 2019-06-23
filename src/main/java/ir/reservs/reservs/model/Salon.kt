package ir.reservs.reservs.model

data class Salon(
        var id: Int,
        var title: String,
        var description: String,
        var thumbnail: String,
        var week: Int,
        var minPrice: Int,
        var maxPrice: Int,
        var cityName: String
)