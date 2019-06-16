package ir.reservs.reservs.ui.main.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ir.reservs.reservs.di.component.ActivityComponent;
import ir.reservs.reservs.ui.base.BaseActivity;
import ir.reservs.reservs.ui.base.BaseView;

public class BaseFragment extends Fragment implements BaseView {
    private BaseActivity mActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            this.mActivity = (BaseActivity) context;
        }
    }

    protected ActivityComponent getActivityComponent() {
        if (mActivity != null) {
            return mActivity.getActivityComponent();
        }
        return null;
    }

    @Override
    public void onResume() {
        super.onResume();
        setup();
    }

    @Override
    public void onError(String error) {

    }

    @Override
    public void onError(int resId) {
        onError(getString(resId));
    }

    @Override
    public void setup() {

    }

}
