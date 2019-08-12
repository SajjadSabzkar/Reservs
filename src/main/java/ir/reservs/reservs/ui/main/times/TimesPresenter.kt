package ir.reservs.reservs.ui.main.times

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ir.huri.jcal.JalaliCalendar
import ir.reservs.reservs.data.DataManager
import ir.reservs.reservs.model.Day
import ir.reservs.reservs.utils.RetrofitError
import ir.reservs.reservs.utils.TimeUtils
import java.util.*

class TimesPresenter(val dataManager: DataManager, val compositeDisposable: CompositeDisposable) : TimesContract.Presenter {
    private var view: TimesContract.View? = null
    private var currentDate: JalaliCalendar
    private var salonId: Int? = null
    private var days: MutableList<Day> = ArrayList()

    init {
        this.currentDate = JalaliCalendar(GregorianCalendar())
    }

    override fun onAttach(view: TimesContract.View) {
        this.view = view
    }


    private fun getTimesFromServer(date: String) {
        view?.loadingState()
        val disposable = dataManager.times(salonId!!, date)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    if (it.size > 0) {
                        view?.normalState()
                        view?.updateTimes(it)
                    } else {
                        view?.emptyState()
                    }
                }, {
                    RetrofitError.handle(view!!, it)
                })
        compositeDisposable.add(disposable)
    }

    fun setSalon(salonId: Int) {
        this.salonId = salonId
    }

    fun initializeViews() {
        var jalaliDate = JalaliCalendar(GregorianCalendar())
        for (i in 0..6) {
            days.add(TimeUtils.getDayFromDate(jalaliDate))
            jalaliDate = jalaliDate.tomorrow
        }
        jalaliDate = JalaliCalendar(GregorianCalendar());
        val date: String = TimeUtils.dateFormat(jalaliDate)
        getTimesFromServer(date)
        view?.initializeViews(days, days[0])
        view?.updateToolbarDate(TimeUtils.dateDisplayFormat(currentDate))
    }

    fun nextDay() {
        if (TimeUtils.getDayFromDate(currentDate).equals(days[6])) {
            days.add(TimeUtils.getDayFromDate(currentDate.tomorrow))
            days.removeAt(0)
            view?.initializeViews(days, days[6])
        }
        view?.clearOldTimes()
        currentDate = currentDate.tomorrow
        getTimesFromServer(TimeUtils.dateFormat(currentDate))
        view?.changeSelectedDay(TimeUtils.getDayFromDate(currentDate))
        view?.updateToolbarDate(TimeUtils.dateDisplayFormat(currentDate))
    }

    fun backDay() {
        if (currentDate == JalaliCalendar(GregorianCalendar())) {
            view?.onError("شما در زمان حال هستید")
            return
        }
        if (TimeUtils.getDayFromDate(currentDate).equals(days[0])) {
            days.add(0, TimeUtils.getDayFromDate(currentDate.yesterday))
            days.removeAt(7)
            view?.initializeViews(days, days[0])
        }
        view?.clearOldTimes()
        currentDate = currentDate.yesterday
        getTimesFromServer(TimeUtils.dateFormat(currentDate))
        view?.changeSelectedDay(TimeUtils.getDayFromDate(currentDate))
        view?.updateToolbarDate(TimeUtils.dateDisplayFormat(currentDate))
    }

    fun goToDate(date: String?) {
        if (date == null) {
            initializeViews()
            return
        }
        val d = TimeUtils.convertStringToDate(date)
        when (compareDates(d, currentDate)) {
            ">" -> {
                days.add(TimeUtils.getDayFromDate(currentDate))
                currentDate = currentDate.tomorrow
                goToDate(date)
            }
            "=" -> {
                getTimesFromServer(TimeUtils.dateFormat(currentDate))
                val selectedIndex: Int
                if (days.size > 7) {
                    selectedIndex = 6
                    days.add(TimeUtils.getDayFromDate(currentDate))
                    days = days.subList(days.lastIndex - 6, days.size)
                } else if (days.size < 7) {
                    selectedIndex = days.size
                    while (days.size < 7) {
                        days.add(TimeUtils.getDayFromDate(currentDate))
                        currentDate = currentDate.tomorrow
                    }
                } else {
                    selectedIndex = 6
                }
                currentDate = TimeUtils.convertStringToDate(days[selectedIndex].date)
                view?.initializeViews(days, days[selectedIndex])
                view?.updateToolbarDate(TimeUtils.dateDisplayFormat(currentDate))
            }
            "<" -> {
                view?.onError("زمان وارد شده نا معتبر است.")
                view?.errorState()
            }
        }

    }

    fun selectDay(day: Day) {
        currentDate = TimeUtils.convertStringToDate(day.date)
        getTimesFromServer(day.date)
        view?.changeSelectedDay(day)
    }

    private fun compareDates(d1: JalaliCalendar, d2: JalaliCalendar): String {
        if (d1.year == d2.year && d1.month == d2.month && d1.day == d2.day) {
            return "="
        } else if (d1.year > d2.year || d1.month > d2.month || d1.day > d2.day) {
            return ">"
        } else {
            return "<"
        }
    }

    override fun onDetach() {
        this.view = null
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.clear()
        }
    }
}