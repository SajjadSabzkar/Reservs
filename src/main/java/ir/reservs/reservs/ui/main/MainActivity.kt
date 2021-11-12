package ir.reservs.reservs.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import ir.reservs.reservs.R
import ir.reservs.reservs.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.aviran.cookiebar2.CookieBar

class MainActivity : BaseActivity() {
    private var navController: NavController? = null

    override fun setup() {
        val host: NavHostFragment = supportFragmentManager
                .findFragmentById(R.id.my_nav_host_fragment) as NavHostFragment? ?: return
        navController = host.navController
        setupBottomNavMenu(navController)
    }

    private fun setupBottomNavMenu(navController: NavController?) {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNav?.setupWithNavController(navController!!)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onError(msg: String, type: String) {
        /*CookieBar.build(this@MainActivity)
                .setTitle(type)
                .setMessage(msg)
                .show()*/
        Toast.makeText(this@MainActivity, msg, Toast.LENGTH_LONG).show()
    }

    fun hideBottomNavigation() {
        bottomNavigation.visibility = View.GONE
    }

    fun showBottomNavigation() {
        bottomNavigation.visibility = View.VISIBLE
    }

    override fun onNavigateUp(): Boolean {
        navController?.navigateUp()
        return super.onNavigateUp()
    }

    override fun onNewIntent(intent: Intent?) {
        navController?.handleDeepLink(intent)
        super.onNewIntent(intent)
    }

    override fun onTokenExpire() {
        navController?.navigate(R.id.sendFragment)
    }
}