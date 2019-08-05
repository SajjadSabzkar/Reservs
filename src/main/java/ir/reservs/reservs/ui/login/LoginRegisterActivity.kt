package ir.reservs.reservs.ui.login

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment

import com.google.android.material.snackbar.Snackbar

import javax.inject.Inject

import dmax.dialog.SpotsDialog
import ir.reservs.reservs.R
import ir.reservs.reservs.ui.base.BaseActivity
import ir.reservs.reservs.ui.login.login.LoginContract
import ir.reservs.reservs.ui.login.login.LoginPresenter
import ir.reservs.reservs.ui.main.MainActivity

class LoginRegisterActivity : BaseActivity() {
    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_login_register)
    }


    override fun onError(msg: String) {

    }

    override fun setup() {
        val host: NavHostFragment = supportFragmentManager
                .findFragmentById(R.id.my_nav_host_fragment) as NavHostFragment? ?: return
        navController = host.navController
    }

    override fun onNavigateUp(): Boolean {
        navController?.navigateUp()
        return super.onNavigateUp()
    }
}
