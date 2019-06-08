package ir.reservs.reservs.ui.main;

import android.os.Bundle;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.reservs.reservs.R;
import ir.reservs.reservs.ui.base.BaseActivity;
import ir.reservs.reservs.ui.main.history.HistoryFragment;
import ir.reservs.reservs.ui.main.reserve.ReserveFragment;
import ir.reservs.reservs.ui.main.settings.SettingsFragment;


public class MainActivity extends BaseActivity implements IMainView {

    @BindView(R.id.bottomNavigation)
    BottomNavigationView mBottomView;

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    ReserveFragment reserveFragment = new ReserveFragment();
    SettingsFragment settingsFragment = new SettingsFragment();
    HistoryFragment historyFragment = new HistoryFragment();
    MainPresenter<MainActivity> mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    @Override
    public void onError(String error) {
        Snackbar.make(mBottomView, error, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onError(long resId) {
        Snackbar.make(mBottomView, (int) resId, Snackbar.LENGTH_LONG).show();
    }

    public void init() {
        mPresenter = new MainPresenter<>(appComponent.getDataManager(), appComponent.getCompositeDisposable());
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), appComponent);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOnTouchListener((v, event) -> true);
        mPresenter.onAttach(this);
        mBottomView.setSelectedItemId(R.id.itemReserve);
        mBottomView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.itemReserve:
                    viewPager.setCurrentItem(0, false);
                    break;
                case R.id.itemSettings:
                    viewPager.setCurrentItem(1, false);
                    break;
                case R.id.itemHistory:
                    viewPager.setCurrentItem(2, false);
                    break;
            }
            return true;
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter = null;
        viewPager = null;

    }

}
