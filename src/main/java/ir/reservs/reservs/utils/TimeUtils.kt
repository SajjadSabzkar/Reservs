package ir.reservs.reservs.utils

object TimeUtils {
    fun diff(time1: String, time2: String): String {
        val hour1: Int = time1.split(":")[0].toInt()
        val min1: Int = time1.split(":")[1].toInt()
        val hour2: Int = time2.split(":")[0].toInt()
        val min2: Int = time2.split(":")[1].toInt()
        var hour = hour2 - hour1
        var min = min2 - min1
        if (min < 0) {
            hour = (hour * 60) + min
            min = hour % 60
            hour /= 60
        }
        return "$hour:$min"
    }

    fun toString(time: String): String {
        val h = time.split(":")[0]
        val m = time.split(":")[1]
        return "$h ساعت و $m دقیقه "
    }
}