package ir.reservs.reservs.ui.main.history;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

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

    private ProgressBar progressBar;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_history, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        historyPresenter.onAttach(this);
        historyRecycler.setAdapter(historyAdapter);
    }



    @Override
    public void setHistoryData(List<ReserveHistory> reserves) {
        historyAdapter.addItems(reserves);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        historyRecycler.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
        historyRecycler.setVisibility(View.VISIBLE);
    }

    @Override
    public void onError(String error) {
        //Snackbar.make(historyRecycler, error, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void setup(View view) {
        getFragmentComponent().inject(this);
        progressBar = view.findViewById(R.id.progressBar);
        historyRecycler = view.findViewById(R.id.historyRecycler);
    }

    @Override
    public void onError(int resId) {
        onError(getString(resId));
    }

    @Override
    public void onDestroy() {
        if (historyPresenter != null) {
            historyPresenter.onDetach();
            historyPresenter = null;
            progressBar = null;
        }
        super.onDestroy();
    }
}
