package ir.reservs.reservs.ui.main.reserve;

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ir.huri.jcal.JalaliCalendar
import ir.reservs.reservs.data.DataManager
import ir.reservs.reservs.model.Day
import ir.reservs.reservs.model.Salon
import ir.reservs.reservs.model.Time
import ir.reservs.reservs.ui.custome.tabs.CustomTabActivityHelper
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
                dataManager.getCurrentUserPhone(), calculateTime(), TimeUtils.dateDisplayFormat(day.date))
    }

    private fun calculateTime(): String {
        return TimeUtils.toString(TimeUtils.diff(time?.start!!, time?.end!!))
    }

    override fun payment() {
        view?.showProgress()
        val callback = "reservs-app://reservs.ir/"
        val disposable = dataManager.reserve(time?.id!!, salon?.id, callback, day?.date!!)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    view?.hideProgress()
                    /*
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it.redirect))
                    view?.context()?.startActivity(intent)
                    */
                    val builder = CustomTabsIntent.Builder()
                    val customTabIntent = builder.build()
                    customTabIntent.launchUrl(view?.context()!!, Uri.parse(it.redirect))
                    CustomTabActivityHelper.openCustomTab((view?.context() as Activity),
                            customTabIntent,
                            Uri.parse(it.redirect)) { it1, it2 ->
                        it1.startActivity(Intent(Intent.ACTION_VIEW, it2))
                    }

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
