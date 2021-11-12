package ir.reservs.reservs.ui.base

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ir.reservs.reservs.ReserveApplication
import ir.reservs.reservs.di.component.ActivityComponent
import ir.reservs.reservs.di.component.DaggerActivityComponent
import ir.reservs.reservs.di.module.ActivityModule
import org.aviran.cookiebar2.CookieBar

abstract class BaseActivity : AppCompatActivity(), BaseActivityContract.View {


    var activityComponent: ActivityComponent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent = DaggerActivityComponent.builder()
                .applicationComponent(ReserveApplication.getComponent())
                .build()
    }

    override fun onResume() {
        super.onResume()
        setup()
    }

    abstract override fun onError(msg: String, type: String)

    override fun onError(resId: Int) {
        onError(getString(resId))
    }


    override fun onDestroy() {
        activityComponent = null
        super.onDestroy()
    }

    abstract fun setup()
}
