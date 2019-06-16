package ir.reservs.reservs.ui.main.salons;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import javax.inject.Inject;

import ir.reservs.reservs.R;
import ir.reservs.reservs.model.Salon;
import ir.reservs.reservs.ui.base.BaseFragment;

public class SalonFragment extends BaseFragment implements SalonContract.View {
    @Inject
    SalonPresenter salonPresenter;
    @Inject
    SalonAdapter salonAdapter;

    RecyclerView salonRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_salons, container, false);
        salonRecyclerView=view.findViewById(R.id.salonRecyclerView);
        getActivityComponent().inject(this);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        salonPresenter.onAttach(this);
        salonRecyclerView.setAdapter(salonAdapter);
    }

    @Override
    public void onError(String error) {
        super.onError(error);
    }

    @Override
    public void onError(int resId) {
        onError(getString(resId));
    }

    @Override
    public void setSalonsData(List<Salon> salons) {
        Log.e("SalonFragment", "setSalonsData" + ": " + salons.size());
        salonAdapter.addItems(salons);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (salonRecyclerView != null)
            salonRecyclerView.setAdapter(null);

        if (salonPresenter != null) {
            salonPresenter.onDetach();
            salonPresenter = null;
        }

    }
}
