package ir.reservs.reservs.ui.main.information;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.snackbar.Snackbar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ir.reservs.reservs.R;
import ir.reservs.reservs.ui.base.BaseFragment;

public class InformationFragment extends BaseFragment implements InformationContract.View {
    @Inject
    InformationPresenter informationPresenter;

    @BindView(R.id.txtName)
    EditText txtName;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_informaion, container, false);
        getActivityComponent().inject(this);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        informationPresenter.onAttach(this);
    }

    @OnClick(R.id.btnConfirm)
    void onConfirm() {
        Log.e("InformationFragment", "onConfirm");
        informationPresenter.confirmInformation(txtName.getText().toString());
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
        Log.e("InformationFragment", "nameUpdated");
        NavHostFragment.findNavController(this).popBackStack();
    }
}
