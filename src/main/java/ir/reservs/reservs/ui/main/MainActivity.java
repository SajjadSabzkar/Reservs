package ir.reservs.reservs.ui.main;

import android.os.Bundle;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import ir.reservs.reservs.R;
import ir.reservs.reservs.ui.base.BaseActivity;


public class MainActivity extends BaseActivity implements MainContract.View {

    BottomNavigationView mBottomView;

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
        super.setup();
        getActivityComponent().inject(this);
        NavController navController = Navigation.findNavController(this, R.id.my_nav_host_fragment);
        //NavigationUI.setupWithNavController(mBottomView, navController);
        mBottomView=findViewById(R.id.bottomNavigation);
        NavigationUI.setupWithNavController(mBottomView, navController);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
