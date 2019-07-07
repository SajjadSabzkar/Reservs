package ir.reservs.reservs.ui.main.results

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.zarinpal.ewallets.purchase.ZarinPal
import dmax.dialog.SpotsDialog

import ir.reservs.reservs.R
import ir.reservs.reservs.ui.base.BaseFragment

import javax.inject.Inject
import kotlin.math.log


class ResultFragment : BaseFragment(), ResultContract.View {


    var resultPresenter: ResultPresenter? = null
        @Inject set
    private var dialog: AlertDialog? = null
    private var imgStatus: ImageView? = null
    private var txtTitleStatus: TextView? = null
    private var txtBodyStatus: TextView? = null
    private var btnResult: Button? = null

    override fun setup(view: View?) {
        fragmentComponent.inject(this)
        resultPresenter?.onAttach(this)
        dialog = SpotsDialog.Builder()
                .setContext(context)
                .setCancelable(false)
                .setMessage(R.string.waiting)
                .build()
        imgStatus = view?.findViewById(R.id.imgStatus)
        txtTitleStatus = view?.findViewById(R.id.txtTitleStatus)
        txtBodyStatus = view?.findViewById(R.id.txtBodyStatus)
        btnResult = view?.findViewById(R.id.btnResult)
        val data = activity?.intent?.data
        resultPresenter?.onReceive(context!!, data!!)
    }

    override fun initializeViews(state: Boolean) {
        if (state) {
            btnResult?.setOnClickListener {
                findNavController().navigate(R.id.resultToHistory)
            }
            return
        }
        imgStatus?.setImageResource(R.drawable.ic_failed)
        txtTitleStatus?.text = getString(R.string.payment_error)
        txtBodyStatus?.text = getString(R.string.return_credit_to_account)
        btnResult?.text = getString(R.string.reservable_locations)
        btnResult?.setOnClickListener {
            findNavController().navigate(R.id.resultToSalons)
        }

    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_result, container, false)
    }

    override fun hideProgress() {
        dialog?.hide()
    }

    override fun showProgress() {
        dialog?.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        resultPresenter?.onDetach()
        dialog = null
    }

}
