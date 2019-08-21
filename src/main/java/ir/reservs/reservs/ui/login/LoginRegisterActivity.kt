package ir.reservs.reservs.ui.login

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import ir.reservs.reservs.R
import ir.reservs.reservs.ui.base.BaseActivity

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
                .findFragmentById(R.id.login_nav_host_fragment) as NavHostFragment? ?: return
        navController = host.navController
    }

    override fun onNavigateUp(): Boolean {
        navController?.navigateUp()
        return super.onNavigateUp()
    }

    override fun onBackPressed() {
        if (navController?.navigateUp() == true) {
            return
        }
        super.onBackPressed()
    }
}
