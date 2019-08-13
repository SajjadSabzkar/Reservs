package ir.reservs.reservs.ui.main.reserve;

import android.app.AlertDialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.transition.TransitionInflater
import ir.reservs.reservs.R
import ir.reservs.reservs.model.Day
import ir.reservs.reservs.model.Salon
import ir.reservs.reservs.model.Time
import ir.reservs.reservs.ui.base.BaseFragment
import ir.reservs.reservs.utils.CommonUtils
import kotlinx.android.synthetic.main.fragment_reserve.*
import javax.inject.Inject
import android.util.Pair as UtilPair

class ReserveFragment : BaseFragment(), ReserveContract.View {

    var reservePresenter: ReservePresenter? = null
        @Inject set

    private var salon: Salon? = null
    private var time: Time? = null
    private var day: Day? = null
    var dialog: AlertDialog? = null
        @Inject set


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_reserve, container, false)
    }

    override fun setup(view: View) {
        fragmentComponent?.inject(this)
        reservePresenter?.onAttach(this)
        salon = arguments?.get("salon") as Salon
        time = arguments?.get("time") as Time
        day = arguments?.get("day") as Day
        //set data from arguments
        txtPrice.text = CommonUtils.moneyDisplayFormat(time?.price, 2)
        txtCityName.text = salon?.cityName
        txtLocation.text = salon?.title
        txtStart.text = time?.start
        txtEnd.text = time?.end

        //set data from dataManager or have calc
        reservePresenter?.initialize(salon!!, time!!, day!!)
        btnPayment.setOnClickListener {
            reservePresenter?.payment()
        }

    }

    override fun initializeViews(name: String, phone: String, time: String, date: String) {
        txtName.text = name
        txtPhone.text = phone
        txtTime.text = time
        txtDate.text = date
    }

    override fun context(): Context? {
        return activity
    }

    override fun showProgress() {
        dialog?.show()
    }

    override fun hideProgress() {
        dialog?.hide()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        if (reservePresenter != null) {
            reservePresenter = null
        }
        if (dialog != null) {
            dialog?.dismiss()
            dialog = null
        }
    }

}
