package ir.reservs.reservs.ui.base

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ir.reservs.reservs.ReserveApplication
import ir.reservs.reservs.di.component.ActivityComponent
import ir.reservs.reservs.di.component.DaggerActivityComponent
import ir.reservs.reservs.di.module.ActivityModule
import ir.reservs.reservs.ui.login.LoginRegisterActivity

abstract class BaseActivity : AppCompatActivity(), BaseActivityContract.View {

    companion object {
        @JvmStatic
        lateinit var activityComponent: ActivityComponent

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent = DaggerActivityComponent.builder()
                .activityModule(ActivityModule(this))
                .applicationComponent(ReserveApplication.getComponent())
                .build()
    }

    override fun onResume() {
        super.onResume()
        setup()
    }

    abstract override fun onError(msg: String)


    override fun onError(resId: Int) {
        onError(getString(resId))
    }

    override fun onTokenExpire() {
        startActivity(Intent(this, LoginRegisterActivity::class.java))
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    abstract fun setup()
}
