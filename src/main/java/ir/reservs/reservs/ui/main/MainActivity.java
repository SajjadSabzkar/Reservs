package ir.reservs.reservs.ui.main;

import android.os.Bundle;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.reservs.reservs.R;
import ir.reservs.reservs.ui.base.BaseActivity;


public class MainActivity extends BaseActivity implements MainContract.View {

    @BindView(R.id.bottomNavigation)
    BottomNavigationView mBottomView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void onError(String error) {
        Snackbar.make(mBottomView, error, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onError(int resId) {
        onError(getString(resId));
    }

    @Override
    public void setup() {
        super.setup();
        ButterKnife.bind(this);
        getActivityComponent().inject(this);
        NavController navController = Navigation.findNavController(this, R.id.my_nav_host_fragment);
        //NavigationUI.setupWithNavController(mBottomView, navController);
        NavigationUI.setupWithNavController(mBottomView, navController);
    }

    public void init() {
/*        mPresenter = new MainPresenter<>(appComponent.getDataManager(), appComponent.getCompositeDisposable());
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), appComponent);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOnTouchListener((v, event) -> true);*/

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
