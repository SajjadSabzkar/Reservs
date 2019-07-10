package ir.reservs.reservs.ui.main.times

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ir.huri.jcal.JalaliCalendar
import ir.reservs.reservs.data.DataManager
import ir.reservs.reservs.model.Day
import ir.reservs.reservs.utils.RetrofitError
import java.util.*

class TimesPresenter(val dataManager: DataManager, val compositeDisposable: CompositeDisposable) : TimesContract.Presenter {
    private var view: TimesContract.View? = null
    private var currentDate: JalaliCalendar
    private var salonId: Int? = null

    init {
        this.currentDate = JalaliCalendar(GregorianCalendar())
    }

    override fun onAttach(view: TimesContract.View) {
        this.view = view
    }

    override fun onDetach() {
        this.view = null
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.clear()
        }
    }

    private fun getTimesFromServer(salon_id: Int, date: String) {
        view?.loadingState()
        val disposable = dataManager.times(salon_id, date)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    view?.normalState()
                    view?.updateTimes(it)
                }, {
                    RetrofitError.handle(view!!, it)
                })
        compositeDisposable.add(disposable)
    }

    fun initializeViews(salon_id: Int) {
        val days = ArrayList<Day>()
        this.salonId = salon_id
        var jalaliDate = JalaliCalendar(GregorianCalendar())
        for (i in 0..6) {
            days.add(getDayFromDate(jalaliDate))
            jalaliDate = jalaliDate.tomorrow
        }
        jalaliDate=JalaliCalendar(GregorianCalendar());
        val date: String = dateFormat(jalaliDate)
        getTimesFromServer(salon_id, date)
        view?.initializeViews(days, days[0])
    }

    fun nextDay() {
        view?.clearOldTimes()
        currentDate = currentDate.tomorrow
        getTimesFromServer(salonId!!, dateFormat(currentDate))
        view?.changeSelectedDay(getDayFromDate(currentDate))
    }

    fun backDay() {
        if (currentDate == JalaliCalendar(GregorianCalendar())) {
            view?.onError("شما در زمان حال هستید")
            return
        }
        view?.clearOldTimes()
        currentDate = currentDate.yesterday
        getTimesFromServer(salonId!!, dateFormat(currentDate))
        view?.changeSelectedDay(getDayFromDate(currentDate))

    }

    private fun getDayFromDate(date: JalaliCalendar): Day {
        val firstLetter = date.dayOfWeekString.substring(0, 1)
        val dateString = dateFormat(date)
        return Day(dateString, firstLetter)
    }

    private fun dateFormat(date: JalaliCalendar?): String {
        if (date != null) {
            return date.year.toString() + "-" + toTwoDigit(date.month) + "-" + date.day
        }
        return date.toString()
    }

    private fun toTwoDigit(num: Int): String {
        return if (num < 10) {
            "0$num"
        } else num.toString()
    }

}