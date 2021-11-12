package ir.reservs.reservs.ui.main.reserve;

import android.content.Intent
import android.net.Uri
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ir.reservs.reservs.data.DataManager
import ir.reservs.reservs.model.Day
import ir.reservs.reservs.model.Salon
import ir.reservs.reservs.model.Time
import ir.reservs.reservs.utils.CommonUtils
import ir.reservs.reservs.utils.RetrofitError
import ir.reservs.reservs.utils.TimeUtils


class ReservePresenter(val dataManager: DataManager, val compositeDisposable: CompositeDisposable) :
        ReserveContract.Presenter {

    var view: ReserveContract.View? = null
    var salon: Salon? = null
    var time: Time? = null
    var day: Day? = null
    var useCredit = false

    override fun onAttach(view: ReserveContract.View) {
        this.view = view
    }

    override fun initialize(salon: Salon, time: Time, day: Day) {
        this.salon = salon
        this.time = time
        this.day = day
        getCreditFromServer()
    }

    private fun calculateTime(): String {
        return TimeUtils.toString(TimeUtils.diff(time?.start!!, time?.end!!))
    }

    fun changeUseCredit(checked: Boolean) {
        useCredit = checked
        if (!checked) {
            view?.updatePriceAfterUserCredit(
                    CommonUtils.moneyDisplayFormat(time?.price.toString(), 0),
                    "(" + CommonUtils.moneyDisplayFormat(dataManager.getCredit().toString(), 0) + ")")
            return
        }
        val userCredit = dataManager.getCredit()
        val reservePrice = time?.price
        val compare = userCredit - reservePrice!!
        var remainingPriceToPay = reservePrice - userCredit
        var remainingUserCredit = userCredit - reservePrice
        if (remainingPriceToPay < 0) {
            remainingPriceToPay = 0
        }
        if (remainingUserCredit < 0) {
            remainingUserCredit = 0
        }
        view?.updatePriceAfterUserCredit(CommonUtils.moneyDisplayFormat(remainingPriceToPay.toString(), 0),
                "(" + CommonUtils.moneyDisplayFormat(remainingUserCredit.toString(), 0) + ")")
    }

    private fun getCreditFromServer() {
        view?.showProgress()
        val disposable = dataManager.getUserInformation().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    view?.hideProgress()
                    dataManager.setCredit(it.credit)
                    initializeView()
                }, {
                    view?.hideProgress()
                })
        compositeDisposable.add(disposable)
    }

    private fun initializeView() {
        view?.initializeViews(dataManager.getCurrentUserName(),
                dataManager.getCurrentUserPhone(),
                calculateTime(),
                TimeUtils.dateDisplayFormat(day?.date!!),
                "(" + CommonUtils.moneyDisplayFormat(dataManager.getCredit().toString(), 0) + ")"
        )
    }

    override fun payment() {
        view?.showProgress()
        val callback = "reservs-app://reservs.ir/"
        val disposable = dataManager.reserve(time?.id.toString(), salon?.id, callback, day?.date!!, this.useCredit)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    view?.hideProgress()
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it.redirect))
                    view?.context()?.startActivity(intent)
                    /*val builder = CustomTabsIntent.Builder()
                    val customTabIntent = builder.build()
                    customTabIntent.launchUrl(view?.context()!!, Uri.parse(it.redirect))
                    CustomTabActivityHelper.openCustomTab((view?.context() as Activity),
                            customTabIntent,
                            Uri.parse(it.redirect)) { it1, it2 ->
                        it1.startActivity(Intent(Intent.ACTION_VIEW, it2))
                    }*/
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
