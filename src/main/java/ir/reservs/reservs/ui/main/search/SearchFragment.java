package ir.reservs.reservs.ui.main.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ir.reservs.reservs.R;
import ir.reservs.reservs.ui.main.base.BaseFragment;

public class SearchFragment extends BaseFragment implements SearchContract.View {

    @BindView(R.id.txtDate)
    TextView txtDate;

    @BindView(R.id.txtCityName)
    TextView txtCityName;

    @BindView(R.id.txtLocation)
    TextView txtLocation;

    @Inject
    SearchPresenter searchPresenter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_search, container, false);
        ButterKnife.bind(this, view);
        getActivityComponent().inject(this);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        searchPresenter.onAttach(this);
    }

    @OnClick(R.id.ConstraintLayoutDate)
    void onDateClick() {
        searchPresenter.getDate();
    }

    @OnClick(R.id.constraintLayoutCity)
    void onCityClick() {
        searchPresenter.getCity();
    }

    @OnClick(R.id.ConstraintLayoutLocation)
    void onLocationClick() {
        searchPresenter.getLocation();
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
            txtCityName = null;
            txtDate = null;
        }

    }
}
