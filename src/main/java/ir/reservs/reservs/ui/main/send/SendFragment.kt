package ir.reservs.reservs.ui.main.send

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import ir.reservs.reservs.R
import ir.reservs.reservs.ui.base.BaseFragment
import ir.reservs.reservs.ui.main.MainActivity
import kotlinx.android.synthetic.main.layout_send.*
import javax.inject.Inject

class SendFragment : BaseFragment(), SendContract.View {
    var sendPresenter: SendPresenter? = null
        @Inject set

    var dialog: AlertDialog? = null
        @Inject set

    override fun setup(view: View) {
        fragmentComponent?.inject(this)
        (activity as MainActivity).hideBottomNavigation()
        sendPresenter?.onAttach(this)
        btnSubmit.setOnClickListener {
            sendPresenter?.sendPhone(txtPhone?.text.toString())
        }
        requireActivity().onBackPressedDispatcher.addCallback(this) {}
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_send , container, false)
    }


    override fun goToVerifyState(phone: String) {
        findNavController().navigate(R.id.action_sendFragment_to_verifyFragment, bundleOf("phone" to phone))
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