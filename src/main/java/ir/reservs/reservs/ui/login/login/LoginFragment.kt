package ir.reservs.reservs.ui.login.login

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import dmax.dialog.SpotsDialog
import ir.reservs.reservs.R
import ir.reservs.reservs.ui.base.BaseContract
import ir.reservs.reservs.ui.base.BaseFragment
import ir.reservs.reservs.ui.main.MainActivity
import javax.inject.Inject

class LoginFragment : BaseFragment(), LoginContract.View {

    private var dialog: AlertDialog? = null
    var loginPresenter: LoginPresenter? = null
        @Inject set



    override fun setup(view: View?) {
        fragmentComponent.inject(this)
        loginPresenter?.onAttach(this)
        val btnCreateAccount: Button? = view?.findViewById(R.id.btnCreateAccount)
        btnCreateAccount?.setOnClickListener { findNavController().navigate(R.id.go_to_register) }
        dialog = SpotsDialog.Builder()
                .setContext(context)
                .setCancelable(false)
                .setMessage(R.string.waiting)
                .build()

        val txtPhone = view?.findViewById<EditText>(R.id.txtPhone)
        val txtPassword = view?.findViewById<EditText>(R.id.txtPassword)
        val btnLogin = view?.findViewById<Button>(R.id.btnLogin)
        btnLogin?.setOnClickListener {
            loginPresenter?.login(txtPhone?.text.toString(),
                    txtPassword?.text.toString())
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