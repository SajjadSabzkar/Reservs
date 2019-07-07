package ir.reservs.reservs.ui.main.reserve;

import android.content.Intent
import android.net.Uri
import android.util.Log
import com.zarinpal.ewallets.purchase.OnCallbackRequestPaymentListener
import ir.reservs.reservs.model.Salon
import ir.reservs.reservs.model.Time
import com.zarinpal.ewallets.purchase.ZarinPal
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import ir.reservs.reservs.data.DataManager
import ir.reservs.reservs.model.Day
import ir.reservs.reservs.utils.RetrofitError
import ir.reservs.reservs.utils.TimeUtils
import android.widget.Toast
import com.zarinpal.ewallets.purchase.PaymentRequest
import com.zarinpal.ewallets.purchase.OnCallbackVerificationPaymentListener
import android.R.attr.data
import android.content.Context
import android.content.Intent.getIntent


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
        view?.initializeViews(dataManager.currentUserName,
                dataManager.currentUserPhone, calculateTime())
    }

    private fun calculateTime(): String {
        return TimeUtils.toString(TimeUtils.diff(time?.start!!, time?.end!!))
    }

    override fun payment() {
        val purchase = ZarinPal.getPurchase(view?.getContext())
        //val payment = ZarinPal.getPaymentRequest()
        val payment = ZarinPal.getSandboxPaymentRequest()
        payment.merchantID = "1ef5dc5a-b65f-11e8-a578-005056a205be"
        payment.amount = time?.price?.toLong()!!
        payment.description = dataManager.currentUserName + ":" + salon?.cityName + "," +
                salon?.title + "," + time?.start + "," + day?.date
        payment.setCallbackURL("reservs-app://reservs.ir/")
        payment.mobile = dataManager.currentUserPhone
        purchase.startPayment(payment) { status: Int, authority: String, _: Uri, intent: Intent ->
            if (status == 100) {
                saveOrderToServer(intent, authority)
            } else {
                view?.onError("پرداخت با خطا مواجه شد")
            }
        }
    }

    private fun saveOrderToServer(intent: Intent, authority: String) {
        Log.e("findBug", "10")
        val disposable = dataManager.reserve(time?.id, salon?.id, authority, day?.date)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    Log.e("findBug", "11")
                    view?.getContext()?.startActivity(intent)
                }, {
                    Log.e("findBug", "12")
                    RetrofitError.handle(view!!, it)
                })
        compositeDisposable.add(disposable)
    }

    override fun onDetach() {
        ZarinPal.getPurchase(null)
        view = null
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.clear()
        }
    }

}
