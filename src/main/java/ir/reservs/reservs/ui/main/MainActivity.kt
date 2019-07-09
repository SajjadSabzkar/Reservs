package ir.reservs.reservs.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import ir.reservs.reservs.R
import ir.reservs.reservs.ui.base.BaseActivity

class MainActivity : BaseActivity(){
    private var navController: NavController? = null

    override fun onError(msg: String) {

    }

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

    override fun onNavigateUp(): Boolean {
        navController?.navigateUp()
        return super.onNavigateUp()
    }

    override fun onNewIntent(intent: Intent?) {
        Log.e("MainActivity", intent.toString())
        navController?.handleDeepLink(intent)
        super.onNewIntent(intent)
    }
}