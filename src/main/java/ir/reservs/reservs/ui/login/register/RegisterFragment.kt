package ir.reservs.reservs.ui.login.register

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import dmax.dialog.SpotsDialog
import ir.reservs.reservs.R
import ir.reservs.reservs.ui.base.BaseFragment
import javax.inject.Inject

class RegisterFragment : BaseFragment(), RegisterContract.View {
    var registerPresenter: RegisterPresenter? = null
        @Inject set

    private var dialog: AlertDialog? = null

    override fun setup(view: View?) {
        fragmentComponent.inject(this)
        dialog = SpotsDialog.Builder()
                .setContext(context)
                .setCancelable(false)
                .setMessage(R.string.waiting)
                .build()
        val txtName = view?.findViewById<TextView>(R.id.txtName)
        val txtPhone = view?.findViewById<TextView>(R.id.txtPhone)
        val txtPassword = view?.findViewById<TextView>(R.id.txtPassword)
        view?.findViewById<Button>(R.id.btnRegister)?.setOnClickListener {
            registerPresenter?.register(txtName?.text.toString(),
                    txtPhone?.text.toString(),
                    txtPassword?.text.toString())
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_register, container, false)
    }

    override fun openMainActivity() {
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