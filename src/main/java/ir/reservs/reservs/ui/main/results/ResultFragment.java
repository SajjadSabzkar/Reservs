package ir.reservs.reservs.ui.main.results;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import ir.reservs.reservs.R;
import ir.reservs.reservs.ui.base.BaseFragment;

public class ResultFragment extends BaseFragment implements ResultContract.View {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_result, container, false);
    }


    @Override
    public void onError(String msg) {

    }

    @Override
    public void setup(View view) {
        getActivityComponent().inject(this);
    }


}
