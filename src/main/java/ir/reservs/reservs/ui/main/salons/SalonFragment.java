package ir.reservs.reservs.ui.main.salons;

import android.os.Bundle;
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


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_salons, container, false);
    }

    @Override
    public void onError(String error) {
        //Snackbar.make(salonRecyclerView, error, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void setup(View view) {
        RecyclerView salonRecyclerView = view.findViewById(R.id.salonRecyclerView);
        getActivityComponent().inject(this);
        salonRecyclerView.setAdapter(salonAdapter);
        salonPresenter.onAttach(this);
    }

    @Override
    public void setSalonsData(List<Salon> salons) {
        salonAdapter.addItems(salons);
    }

    @Override
    public void onDestroyView() {
        if (salonPresenter != null) {
            salonPresenter.onDetach();
            salonPresenter = null;
        }
        super.onDestroyView();
    }

}
