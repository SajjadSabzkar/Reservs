package ir.reservs.reservs.ui.base;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import ir.reservs.reservs.di.component.ActivityComponent;

public abstract class BaseFragment extends Fragment implements BaseContract.View {
    private BaseActivity mActivity;

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


    public abstract void onError(String msg);

    public void onError(int resId) {
        onError(getString(resId));
    }
}
