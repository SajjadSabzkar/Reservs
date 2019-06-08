package ir.reservs.reservs.ui.main;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import ir.reservs.reservs.di.component.ApplicationComponent;
import ir.reservs.reservs.ui.main.history.HistoryFragment;
import ir.reservs.reservs.ui.main.reserve.ReserveFragment;
import ir.reservs.reservs.ui.main.settings.SettingsFragment;

public class PagerAdapter extends FragmentPagerAdapter {
    private ApplicationComponent applicationComponent;

    PagerAdapter(@NonNull FragmentManager fm, ApplicationComponent applicationComponent) {
        super(fm);
        this.applicationComponent = applicationComponent;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                ReserveFragment reserveFragment = new ReserveFragment();
                reserveFragment.setComponent(applicationComponent);
                return reserveFragment;
            case 1:
                SettingsFragment settingsFragment = new SettingsFragment();
                settingsFragment.setComponent(applicationComponent);
                return settingsFragment;
            case 2:
                HistoryFragment historyFragment = new HistoryFragment();
                historyFragment.setComponent(applicationComponent);
                return historyFragment;
            default:
                return new ReserveFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
