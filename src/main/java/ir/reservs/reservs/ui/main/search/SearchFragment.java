package ir.reservs.reservs.ui.main.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import javax.inject.Inject;

import ir.reservs.reservs.R;
import ir.reservs.reservs.ui.base.BaseFragment;

public class SearchFragment extends BaseFragment implements SearchContract.View {

    private TextView txtDate;
    private TextView txtCityName;
    private TextView txtLocation;

    @Inject
    SearchPresenter searchPresenter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_search, container, false);
        getActivityComponent().inject(this);
        txtCityName = view.findViewById(R.id.txtCityName);
        txtDate = view.findViewById(R.id.txtDate);
        txtLocation = view.findViewById(R.id.txtLocation);
        ConstraintLayout constraintLayoutDate, constraintLayoutCity, constraintLayoutLocation;
        constraintLayoutDate=view.findViewById(R.id.constraintLayoutLocation);
        constraintLayoutCity=view.findViewById(R.id.constraintLayoutCity);
        constraintLayoutLocation=view.findViewById(R.id.constraintLayoutLocation);
        constraintLayoutDate.setOnClickListener((v)->searchPresenter.getDate());
        constraintLayoutCity.setOnClickListener((v)->searchPresenter.getCity());
        constraintLayoutLocation.setOnClickListener((v)->searchPresenter.getLocation());
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        searchPresenter.onAttach(this);
    }

    @Override
    public void setDate(int year, int month, int day) {
        txtDate.setText(year + "/" + month + "/" + day);
    }

    @Override
    public void setCity(String city) {
        txtCityName.setText(city);
    }

    @Override
    public void setLocation(String location) {
        txtLocation.setText(location);
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
    public void onDestroy() {
        super.onDestroy();
        if (searchPresenter != null) {
            searchPresenter.onDetach();
            searchPresenter = null;
        }

    }
}
