package ir.reservs.reservs.ui.main.reserve;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ir.reservs.reservs.R;
import ir.reservs.reservs.ui.base.BaseFragment;
import android.widget.Toast
import com.zarinpal.ewallets.purchase.ZarinPal
import android.net.Uri
import android.widget.TextView
import ir.reservs.reservs.model.Salon
import ir.reservs.reservs.model.Time
import javax.inject.Inject


class ReserveFragment : BaseFragment(), ReserveContract.View {

    var reservePresenter: ReservePresenter? = null
        @Inject set

    var txtPrice: TextView? = null
    var txtName: TextView? = null
    var txtPhone: TextView? = null
    var txtCityName: TextView? = null
    var txtLocation: TextView? = null
    var txtStart: TextView? = null
    var txtEnd: TextView? = null
    var txtTime: TextView? = null
    var salon: Salon? = null
    var time: Time? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_reserve, container, false)
    }

    override fun setup(view: View?) {
        fragmentComponent.inject(this)
        reservePresenter?.onAttach(this)
        salon = arguments?.get("salon") as Salon
        time = arguments?.get("time") as Time
        //set data from arguments
        view?.findViewById<TextView>(R.id.txtPrice)?.text = time?.price
        view?.findViewById<TextView>(R.id.txtCity)?.text = salon?.cityName
        view?.findViewById<TextView>(R.id.txtLocation)?.text = salon?.title
        view?.findViewById<TextView>(R.id.txtStart)?.text = time?.start
        view?.findViewById<TextView>(R.id.txtEnd)?.text = time?.end
        //set data from dataManager or have calc
        txtName = view?.findViewById(R.id.txtName)
        txtPhone = view?.findViewById(R.id.txtPhone)
        txtTime = view?.findViewById(R.id.txtTime)
        reservePresenter?.initialize(salon!!, time!!)
    }

    override fun initializeViews(name: String, phone: String, time: String) {
        txtName?.text = name
        txtPhone?.text = phone
        txtTime?.text = time
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (reservePresenter != null) {
            reservePresenter = null
            txtPrice = null
            txtName = null
            txtPhone = null
            txtCityName = null
            txtLocation = null
            txtStart = null
            txtEnd = null
            txtTime = null
        }
    }

}
