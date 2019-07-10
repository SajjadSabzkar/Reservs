package ir.reservs.reservs.ui.login.register

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import dmax.dialog.SpotsDialog
import ir.reservs.reservs.R
import ir.reservs.reservs.ui.base.BaseFragment
import ir.reservs.reservs.ui.main.MainActivity
import javax.inject.Inject

class RegisterFragment : BaseFragment(), RegisterContract.View {
    var registerPresenter: RegisterPresenter? = null
        @Inject set

    private var dialog: AlertDialog? = null

    override fun setup(view: View) {
        fragmentComponent?.inject(this)
        registerPresenter?.onAttach(this)
        dialog = SpotsDialog.Builder()
                .setContext(context)
                .setCancelable(false)
                .setMessage(R.string.waiting)
                .build()
        val txtName = view.findViewById<TextView>(R.id.txtName)
        val txtPhone = view.findViewById<TextView>(R.id.txtPhone)
        val txtPassword = view.findViewById<TextView>(R.id.txtPassword)
        view.findViewById<Button>(R.id.btnRegister)?.setOnClickListener {
            registerPresenter?.register(txtName?.text.toString(),
                    txtPhone?.text.toString(),
                    txtPassword?.text.toString())
        }
        view.findViewById<TextView>(R.id.txtLogin)?.setOnClickListener {
            findNavController().navigate(R.id.registerToLogin)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_register, container, false)
    }

    override fun openMainActivity() {
        startActivity(Intent(context, MainActivity::class.java))
    }

    override fun showProgress() {
        dialog?.show()
    }

    override fun hideProgress() {
        dialog?.hide()
    }

    override fun onDestroyView() {
        if (dialog != null) {
            dialog?.dismiss()
            dialog = null
        }
        super.onDestroyView()
    }


}