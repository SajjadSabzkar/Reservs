package ir.reservs.reservs.ui.main.history;

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

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.reservs.reservs.R;
import ir.reservs.reservs.model.ReserveHistory;
import ir.reservs.reservs.ui.base.BaseFragment;

public class HistoryFragment extends BaseFragment implements HistoryContract.View {
    @Inject
    HistoryAdapter historyAdapter;

    @Inject
    HistoryPresenter historyPresenter;

    @BindView(R.id.historyRecycler)
    RecyclerView historyRecycler;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_history, container, false);
        ButterKnife.bind(this, view);
        getActivityComponent().inject(this);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        historyPresenter.onAttach(this);
        historyRecycler.setAdapter(historyAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (historyRecycler != null)
            historyRecycler.setAdapter(null);

        if (historyPresenter != null) {
            historyPresenter.onDetach();
            historyPresenter = null;
        }
    }

    @Override
    public void setHistoryData(List<ReserveHistory> reserves) {
        Log.e("HistoryFragment", "setHistoryData" + ": " + reserves.size());
        historyAdapter.addItems(reserves);
    }

    @Override
    public void setup() {
        super.setup();
    }

    @Override
    public void onError(String error) {
        super.onError(error);
    }

    @Override
    public void onError(int resId) {
        onError(getString(resId));
    }
}
