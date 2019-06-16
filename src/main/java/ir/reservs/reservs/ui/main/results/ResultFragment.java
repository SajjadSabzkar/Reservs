package ir.reservs.reservs.ui.main.results;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import butterknife.ButterKnife;
import ir.reservs.reservs.R;
import ir.reservs.reservs.ui.main.base.BaseFragment;

public class ResultFragment extends BaseFragment implements ResultContract.View {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result, container, false);
        ButterKnife.bind(this, view);
        getActivityComponent().inject(this);
        return view;
    }


    @Override
    public void onError(String msg) {

    }

    @Override
    public void onError(int resId) {
        onError(getString(resId));
    }

    @Override
    public void setup() {

    }
}
