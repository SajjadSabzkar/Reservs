package ir.reservs.reservs.utils

import ir.huri.jcal.JalaliCalendar
import ir.reservs.reservs.model.Day
import java.util.*

/**
 * @author rfmhb2
 */
object TimeUtils {
    /**
     * @param time1:String time like(13:30)
     * @param time2:String time like(15:00)
     * @return String time like(1:30)
     **/
    fun diff(time1: String, time2: String): String {
        val hour1: Int = time1.split(":")[0].toInt()
        val min1: Int = time1.split(":")[1].toInt()
        val hour2: Int = time2.split(":")[0].toInt()
        val min2: Int = time2.split(":")[1].toInt()
        var hour = hour2 - hour1
        var min = min2 - min1
        if (hour1 > hour2) {
            hour = (24 + hour2) - hour1
        }
        if (min < 0) {
            hour = (hour * 60) + min
            min = hour % 60
            hour /= 60
        }
        return "$hour:$min"
    }

    /**
     * @param time String like(1:30)
     * @return String like ۱ ساعت و ۳۰ دقیقه
     * change time format to string like(1:30 -> ۱ ساعت و ۳۰ دقیقه)
     */
    fun toString(time: String): String {
        val h = time.split(":")[0]
        val m = time.split(":")[1]
        return "$h ساعت و $m دقیقه"
    }

    /**
     * @param date: JalaliCalendar
     * @return Day
     */
    fun getDayFromDate(date: JalaliCalendar): Day {
        val firstLetter = date.dayOfWeekString.substring(0, 1)
        val dateString = dateFormat(date)
        return Day(dateString, firstLetter)
    }

    fun getDayFromDate(date: String): Day {
        return getDayFromDate(convertStringToDate(date))
    }

    fun convertStringToDate(date: String): JalaliCalendar {
        val arr = date.split("-")
        return JalaliCalendar(arr[0].toInt(), arr[1].toInt(), arr[2].toInt())
    }

    fun dateFormat(date: JalaliCalendar?): String {
        return date?.year.toString() + "-" + toTwoDigit(date?.month!!) + "-" + toTwoDigit(date.day)
    }

    fun dateFormat(year: Int, month: Int, day: Int): String {
        return "$year-${toTwoDigit(month)}-${toTwoDigit(day)}"
    }

    fun dateDisplayFormat(year: Int, month: Int, day: Int): String {
        val date = JalaliCalendar(year, month, day)
        return TimeUtils.dateDisplayFormat(date)
    }

    fun dateDisplayFormat(date: JalaliCalendar): String {
        return "${date.dayOfWeekString} ${date.day} ${date.monthString} ${date.year}"
    }

    fun dateDisplayFormat(date: String): String {
        return dateDisplayFormat(convertStringToDate(date))
    }

    fun toTwoDigit(num: Int): String {
        return if (num < 10) {
            "0$num"
        } else num.toString()
    }

    fun compareDates(d1: JalaliCalendar, d2: JalaliCalendar): String {
        return if (d1.year == d2.year && d1.month == d2.month && d1.day == d2.day) {
            "="
        } else if (d1.year > d2.year || d1.month > d2.month || d1.day > d2.day) {
            ">"
        } else {
            "<"
        }
    }

    fun timestampToPersian(date: Long): String? {
        val jcal = JalaliCalendar(Date(date))
        return jcal.year.toString() + "/" + jcal.month.toString() + "/" + jcal.day.toString()
    }

    fun persianToTimestamp(persianDate: String?): Long? {
        val birthdaySplit = persianDate?.split("/")
        val year = birthdaySplit?.get(0)?.toInt()
        val month = birthdaySplit?.get(1)?.toInt()
        val day = birthdaySplit?.get(2)?.toInt()
        if (day != null && month != null && year != null) {
            return JalaliCalendar(year, month, day).toGregorian().timeInMillis
        }
        return null;
    }
}