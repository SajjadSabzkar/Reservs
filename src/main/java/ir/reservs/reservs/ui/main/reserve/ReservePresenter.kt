package ir.reservs.reservs.ui.main.reserve;

import android.content.Intent
import android.net.Uri
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ir.huri.jcal.JalaliCalendar
import ir.reservs.reservs.data.DataManager
import ir.reservs.reservs.model.Day
import ir.reservs.reservs.model.Salon
import ir.reservs.reservs.model.Time
import ir.reservs.reservs.utils.RetrofitError
import ir.reservs.reservs.utils.TimeUtils


class ReservePresenter(val dataManager: DataManager, val compositeDisposable: CompositeDisposable) :
        ReserveContract.Presenter {

    var view: ReserveContract.View? = null
    var salon: Salon? = null
    var time: Time? = null
    var day: Day? = null


    override fun onAttach(view: ReserveContract.View) {
        this.view = view
    }

    override fun initialize(salon: Salon, time: Time, day: Day) {
        this.salon = salon
        this.time = time
        this.day = day
        view?.initializeViews(dataManager.getCurrentUserName(),
                dataManager.getCurrentUserPhone(), calculateTime(), dateToString())
    }

    private fun calculateTime(): String {
        return TimeUtils.toString(TimeUtils.diff(time?.start!!, time?.end!!))
    }

    private fun dateToString(): String {
        val da = day?.date?.split("-")
        val year = da!![0].toInt()
        val mount = da[1].toInt()
        val day = da[2].toInt()
        val jdate = JalaliCalendar(year, mount, day)
        return "${jdate.dayOfWeekString} ${jdate.day} ${jdate.monthString}"
    }

    override fun payment() {
        view?.showProgress()
        val callback = "reservs-app://reservs.ir/"
        val disposable = dataManager.reserve(time?.id!!, salon?.id, callback, day?.date!!)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    view?.hideProgress()
                    Log.e("ReservePresenter", it.redirect)
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it.redirect))
                    view?.context()?.startActivity(intent)
                }, {
                    view?.hideProgress()
                    RetrofitError.handle(view!!, it)
                })
        compositeDisposable.add(disposable)
    }

    override fun onDetach() {
        view = null
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.clear()
        }
    }

}
