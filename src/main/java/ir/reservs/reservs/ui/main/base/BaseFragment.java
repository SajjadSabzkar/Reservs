package ir.reservs.reservs.ui.main.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ir.reservs.reservs.di.component.ApplicationComponent;
import ir.reservs.reservs.ui.base.IView;

public class BaseFragment extends Fragment implements IView {
    protected ApplicationComponent appComponent;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void setComponent(ApplicationComponent appComponent) {
        this.appComponent = appComponent;
    }

    @Override
    public void onError(String error) {

    }

    @Override
    public void onError(long resId) {

    }

    @Override
    public void init() {

    }
}
