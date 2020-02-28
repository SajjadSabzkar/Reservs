package ir.reservs.reservs.ui.login.send

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import ir.reservs.reservs.R
import ir.reservs.reservs.ui.base.BaseFragment
import kotlinx.android.synthetic.main.layout_send.*
import javax.inject.Inject

class SendFragment() : BaseFragment(), SendContract.View {

    var dialog: AlertDialog? = null
        @Inject set

    var sendPresenter: SendPresenter? = null
        @Inject set


    override fun setup(view: View) {
        fragmentComponent?.inject(this)
        sendPresenter?.onAttach(this)
        btnSubmit.setOnClickListener {
            sendPresenter?.sendPhone(txtPhone?.text.toString())
        }

    }

    override fun goToVerifyState(phone: String) {
        val bundle = bundleOf("phone" to phone)
        findNavController().navigate(R.id.verifyFragment, bundle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_send, container, false)
    }


    override fun showProgress() {
        dialog?.show()
    }

    override fun hideProgress() {
        dialog?.hide()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (dialog != null) {
            dialog?.dismiss()
            dialog = null
        }
    }


}