package ir.reservs.reservs.ui.main;

import android.os.Bundle;
import android.util.Log;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import ir.reservs.reservs.R;
import ir.reservs.reservs.ui.base.BaseActivity;


public class MainActivity extends BaseActivity implements MainContract.View {
    BottomNavigationView mBottomView;
    NavController navController;
    AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public void onError(String error) {
    }

    @Override
    public void onError(int resId) {
    }

    @Override
    public void setup() {
        getActivityComponent().inject(this);
        navController = Navigation.findNavController(this, R.id.my_nav_host_fragment);
        mBottomView = findViewById(R.id.bottomNavigation);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupWithNavController(mBottomView, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        Log.e("MainActivity", "onSupportNavigateUp");
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

}
