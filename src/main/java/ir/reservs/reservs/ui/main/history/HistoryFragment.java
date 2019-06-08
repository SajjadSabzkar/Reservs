package ir.reservs.reservs.ui.main.history;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ir.reservs.reservs.R;
import ir.reservs.reservs.model.ReserveHistory;
import ir.reservs.reservs.ui.main.base.BaseFragment;

public class HistoryFragment extends BaseFragment implements IHistoryView {

    private HistoryAdapter historyAdapter;
    private HistoryPresenter historyPresenter;
    private RecyclerView historyRecycler;

    public static HistoryFragment newInstance(int page, String title) {
        HistoryFragment historyFragment = new HistoryFragment();
        return historyFragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_history, container, false);
        historyRecycler = view.findViewById(R.id.historyRecycler);
        historyAdapter = new HistoryAdapter();
        historyRecycler.setAdapter(historyAdapter);
        historyPresenter = new HistoryPresenter(appComponent.getDataManager(), appComponent.getCompositeDisposable());
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        historyPresenter.onAttach(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        historyRecycler.setAdapter(null);
        historyPresenter.onDetach();
        historyPresenter = null;
    }

    @Override
    public void setHistoryData(List<ReserveHistory> reserves) {
        historyAdapter.addItems(reserves);
    }
}
