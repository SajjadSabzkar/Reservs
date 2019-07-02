package ir.reservs.reservs.ui.main.details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import ir.reservs.reservs.R;
import ir.reservs.reservs.ui.base.BaseFragment;

public class DetailsFragment extends BaseFragment implements DetailsContract.View {

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
        getFragmentComponent().inject(this);
    }


}
