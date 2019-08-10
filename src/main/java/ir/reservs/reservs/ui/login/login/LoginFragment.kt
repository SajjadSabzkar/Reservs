package ir.reservs.reservs.ui.login.login

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.google.firebase.iid.FirebaseInstanceId
import ir.reservs.reservs.R
import ir.reservs.reservs.ui.base.BaseFragment
import ir.reservs.reservs.ui.main.MainActivity
import kotlinx.android.synthetic.main.layout_login.*
import javax.inject.Inject

class LoginFragment : BaseFragment(), LoginContract.View {

    var dialog: AlertDialog? = null
        @Inject set

    var loginPresenter: LoginPresenter? = null
        @Inject set
    var fcmToken: String? = null

    override fun setup(view: View) {
        fragmentComponent?.inject(this)
        loginPresenter?.onAttach(this)
        FirebaseInstanceId.getInstance().instanceId.addOnCompleteListener {
            if (!it.isSuccessful) {
                return@addOnCompleteListener
            }
            fcmToken = it.result?.token
        }
        txtCreateAccount.setOnClickListener {
            findNavController().navigate(R.id.go_to_register)
        }

        btnLogin?.setOnClickListener {
            loginPresenter?.login(txtPhone?.text.toString(),
                    txtPassword?.text.toString(), fcmToken!!)
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_login, container, false)
    }

    override fun openMainActivity() {
        startActivity(Intent(context, MainActivity::class.java))
        activity?.finish()
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