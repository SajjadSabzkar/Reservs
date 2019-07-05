package ir.reservs.reservs.ui.main.reserve;

import ir.reservs.reservs.model.Salon
import ir.reservs.reservs.model.Time
import com.zarinpal.ewallets.purchase.ZarinPal
import io.reactivex.disposables.CompositeDisposable
import ir.reservs.reservs.data.DataManager
import ir.reservs.reservs.utils.TimeUtils


class ReservePresenter(val dataManager: DataManager, val compositeDisposable: CompositeDisposable) :
        ReserveContract.Presenter {

    var view: ReserveContract.View? = null
    var salon: Salon? = null
    var time: Time? = null

    override fun onAttach(view: ReserveContract.View) {
        this.view = view
    }

    override fun initialize(salon: Salon, time: Time) {
        this.salon = salon
        this.time = time
        view?.initializeViews(dataManager.currentUserName,
                dataManager.currentUserPhone, calculateTime())
    }

    private fun calculateTime(): String {
        return TimeUtils.toString(TimeUtils.diff(time?.start!!, time?.end!!))
    }

    fun payment(salon: Salon, time: Time, date: String) {
        val purchase = ZarinPal.getPurchase(null)
        //val payment = ZarinPal.getPaymentRequest()
        //If you will test on our sandbox, you can use it:
        val payment = ZarinPal.getSandboxPaymentRequest()
        payment.merchantID = "1ef5dc5a-b65f-11e8-a578-005056a205be"
        payment.amount = time.price.toLong()
        payment.description = dataManager.currentUserName + ":" + salon.cityName + "," +
                salon.title + "," + time.start + "," + date
        payment.setCallbackURL("reservs://payment")
        payment.mobile = dataManager.currentUserPhone
        purchase.startPayment(payment, null)
    }

    override fun onDetach() {
        view = null
    }

}
