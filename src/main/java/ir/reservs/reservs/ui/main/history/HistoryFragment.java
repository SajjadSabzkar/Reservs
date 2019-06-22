package ir.reservs.reservs.ui.main.history;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import javax.inject.Inject;

import ir.reservs.reservs.R;
import ir.reservs.reservs.model.ReserveHistory;
import ir.reservs.reservs.ui.base.BaseFragment;

public class HistoryFragment extends BaseFragment implements HistoryContract.View {
    @Inject
    HistoryAdapter historyAdapter;

    @Inject
    HistoryPresenter historyPresenter;


    private RecyclerView historyRecycler;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_history, container, false);
        getActivityComponent().inject(this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        historyPresenter.onAttach(this);
        historyRecycler.setAdapter(historyAdapter);
    }

    @Override
    public void onDestroy() {
        historyRecycler.setAdapter(null);
        if (historyPresenter != null) {
            historyPresenter.onDetach();
            historyPresenter = null;
        }
        super.onDestroy();
    }

    @Override
    public void setHistoryData(List<ReserveHistory> reserves) {
        Log.e("HistoryFragment", "setHistoryData" + ": " + reserves.size());
        historyAdapter.addItems(reserves);
    }

    @Override
    public void onError(String error) {
        Snackbar.make(historyRecycler, error, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void setup(View view) {
        historyRecycler = view.findViewById(R.id.historyRecycler);
    }

    @Override
    public void onError(int resId) {
        onError(getString(resId));
    }
}
