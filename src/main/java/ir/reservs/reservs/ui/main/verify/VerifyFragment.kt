package ir.reservs.reservs.ui.main.verify

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.google.firebase.iid.FirebaseInstanceId
import ir.reservs.reservs.R
import ir.reservs.reservs.ui.base.BaseFragment
import ir.reservs.reservs.ui.main.MainActivity
import kotlinx.android.synthetic.main.layout_verify.*
import javax.inject.Inject

class VerifyFragment : BaseFragment(), VerifyContract.View {
    var dialog: AlertDialog? = null
        @Inject set

    var fcmToken: String? = null
    var phone: String? = null
    var verifyPresenter: VerifyPresenter? = null
        @Inject set

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_verify, container, false)
    }

    override fun backToPhoneState(phone: String) {
        val bundle = bundleOf("phone" to phone)
        findNavController().navigate(R.id.sendFragment, bundle)
    }

    override fun goToEditInfo() {
        findNavController().navigate(R.id.action_verifyFragment_to_informationFragment)
    }

    override fun goToMyReserves() {
        findNavController().navigate(R.id.action_verifyFragment_to_historyFragment)
    }

    override fun showProgress() {
        dialog?.show()
    }

    override fun hideProgress() {
        dialog?.hide()
    }

    override fun setup(view: View) {
        fragmentComponent?.inject(this)
        verifyPresenter?.onAttach(this)
        phone = arguments?.getString("phone")
        txtPhone.setText(phone)
        FirebaseInstanceId.getInstance().instanceId.addOnCompleteListener {
            if (!it.isSuccessful) {
                return@addOnCompleteListener
            }
            fcmToken = it.result?.token
        }
        btnSubmit.setOnClickListener {
            verifyPresenter?.confirmCode(phone!!, txtConfirm.text.toString())
        }
        txtChangePhone.setOnClickListener {
            findNavController().navigate(R.id.action_verifyFragment_to_sendFragment, bundleOf("phone" to phone))
        }
        requireActivity().onBackPressedDispatcher.addCallback(this) {}
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (dialog != null) {
            dialog?.dismiss()
            dialog = null
        }
    }
}