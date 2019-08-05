package ir.reservs.reservs.ui.splash

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import ir.reservs.reservs.R
import ir.reservs.reservs.data.DataManager
import ir.reservs.reservs.ui.base.BaseActivity
import ir.reservs.reservs.ui.login.LoginRegisterActivity
import ir.reservs.reservs.ui.main.MainActivity
import javax.inject.Inject

class SplashActivity : BaseActivity(), SplashContract.View {

    private var txtVersion: TextView? = null


    var dataManager: DataManager? = null
        @Inject set

    var splashPresenter: SplashPresenter? = null
        @Inject set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_splash)
    }

    override fun setup() {
        txtVersion = findViewById(R.id.txtVersion)
        activityComponent!!.inject(this)

        splashPresenter!!.onAttach(this)
    }

    override fun openMainActivity() {
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
        finish()
    }

    override fun openLoginActivity() {
        val i = Intent(this, LoginRegisterActivity::class.java)
        startActivity(i)
        finish()
    }

    override fun onError(msg: String) {
        Snackbar.make(txtVersion!!, msg, Snackbar.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (splashPresenter != null) {
            splashPresenter!!.onDetach()
            splashPresenter = null
        }
    }

    override fun emptyState() {

    }

    override fun errorState() {

    }

    override fun loadingState() {

    }

    override fun normalState() {

    }
}
