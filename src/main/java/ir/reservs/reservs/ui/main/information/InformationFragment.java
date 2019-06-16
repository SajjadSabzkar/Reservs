package ir.reservs.reservs.ui.main.information;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.snackbar.Snackbar;

import javax.inject.Inject;

import butterknife.OnClick;
import ir.reservs.reservs.R;
import ir.reservs.reservs.ui.base.BaseFragment;

public class InformationFragment extends BaseFragment implements InformationContract.View {
    @Inject
    InformationPresenter informationPresenter;

    EditText txtName;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_informaion, container, false);
        getActivityComponent().inject(this);
        txtName = view.findViewById(R.id.txtName);
        Button btnConfirm = view.findViewById(R.id.btnConfirm);
        btnConfirm.setOnClickListener((e)->informationPresenter.confirmInformation(txtName.getText().toString()));
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        informationPresenter.onAttach(this);
    }

    @OnClick(R.id.btnConfirm)
    void onConfirm() {

    }

    @Override
    public void onError(String error) {
        Snackbar.make(txtName, error, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onError(int resId) {
        onError(getString(resId));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        informationPresenter.onDetach();
    }

    @Override
    public void nameUpdated(String name) {
        onError(String.format("نام شما به %s تغییر کرد.", name));
        NavHostFragment.findNavController(this).popBackStack();
    }
}
