package ir.reservs.reservs.ui.login.forget

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import ir.reservs.reservs.R
import ir.reservs.reservs.ui.base.BaseFragment
import kotlinx.android.synthetic.main.layout_forget.*
import kotlinx.android.synthetic.main.layout_login.*
import kotlinx.android.synthetic.main.layout_login.txtPhone
import javax.inject.Inject

class ForgetFragment() : BaseFragment(), ForgetContract.View {

    var dialog: AlertDialog? = null
        @Inject set

    var forgetPresenter: ForgetPresenter? = null
        @Inject set

    override fun setup(view: View) {
        fragmentComponent?.inject(this)
        forgetPresenter?.onAttach(this)
        imgBack.setOnClickListener {
            findNavController().navigate(R.id.forgetToLogin)
        }

        btnSendPassword.setOnClickListener {
            forgetPresenter?.login(txtPhone?.text.toString())
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_forget, container, false)
    }

    override fun goToLoginFragment() {
        findNavController().navigate(R.id.forgetToLogin)
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