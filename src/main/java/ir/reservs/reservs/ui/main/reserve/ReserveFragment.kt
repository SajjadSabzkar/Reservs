package ir.reservs.reservs.ui.main.reserve;

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import dmax.dialog.SpotsDialog
import ir.reservs.reservs.R
import ir.reservs.reservs.model.Day
import ir.reservs.reservs.model.Salon
import ir.reservs.reservs.model.Time
import ir.reservs.reservs.ui.base.BaseFragment
import javax.inject.Inject


class ReserveFragment : BaseFragment(), ReserveContract.View {

    var reservePresenter: ReservePresenter? = null
        @Inject set

    private var txtName: TextView? = null
    private var txtPhone: TextView? = null
    private var txtTime: TextView? = null
    private var salon: Salon? = null
    private var time: Time? = null
    private var day: Day? = null
    private var dialog: AlertDialog? = null

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
        view.findViewById<TextView>(R.id.txtPrice)?.text = time?.price
        view.findViewById<TextView>(R.id.txtCity)?.text = salon?.cityName
        view.findViewById<TextView>(R.id.txtLocation)?.text = salon?.title
        view.findViewById<TextView>(R.id.txtStart)?.text = time?.start
        view.findViewById<TextView>(R.id.txtEnd)?.text = time?.end
        view.findViewById<TextView>(R.id.txtDate)?.text = day?.date
        //set data from dataManager or have calc
        txtName = view.findViewById(R.id.txtName)
        txtPhone = view.findViewById(R.id.txtPhone)
        txtTime = view.findViewById(R.id.txtTime)
        dialog = SpotsDialog.Builder()
                .setContext(context)
                .setCancelable(false)
                .setMessage(R.string.waiting)
                .build()
        reservePresenter?.initialize(salon!!, time!!, day!!)
        view.findViewById<Button>(R.id.btnPayment)?.setOnClickListener {
            reservePresenter?.payment()
        }

    }

    override fun initializeViews(name: String, phone: String, time: String) {
        txtName?.text = name
        txtPhone?.text = phone
        txtTime?.text = time
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
            txtName = null
            txtPhone = null
            txtTime = null
        }
        if (dialog != null) {
            dialog?.dismiss()
            dialog = null
        }
    }

}
