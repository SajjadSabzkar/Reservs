package ir.reservs.reservs.ui.main.times;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import javax.inject.Inject;

import ir.reservs.reservs.R;
import ir.reservs.reservs.model.Day;
import ir.reservs.reservs.model.Time;
import ir.reservs.reservs.ui.base.BaseFragment;

public class TimesFragment extends BaseFragment implements TimesContract.View {
    @Inject
    WeekDayAdapter weekDayAdapter;

    @Inject
    TimesAdapter timesAdapter;

    @Inject
    TimesPresenter timesPresenter;

    private ProgressBar timesProgressBar;

    private RecyclerView timeRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_times, container, false);
    }

    @Override
    public void onError(String msg) {

    }

    @Override
    public void setup(View view) {
        getFragmentComponent().inject(this);
        RecyclerView dayRecyclerView = view.findViewById(R.id.dayRecyclerView);

        timeRecyclerView = view.findViewById(R.id.timesRecyclerView);
        timesProgressBar = view.findViewById(R.id.timesProgressBar);
        view.findViewById(R.id.nextConstraint).setOnClickListener(v -> {
            timesPresenter.nextDay();
            clearOldTimes();
        });
        view.findViewById(R.id.backConstraint).setOnClickListener(v -> {
            timesPresenter.backDay();
            clearOldTimes();
        });
        dayRecyclerView.setAdapter(weekDayAdapter);
        timeRecyclerView.setAdapter(timesAdapter);
        timesPresenter.onAttach(this);
        timesPresenter.initializeViews(getArguments().getInt("salon_id"));
    }

    @Override
    public void updateTimes(@NotNull List<Time> times) {
        Log.e("TimesFragment", "updateTimes: " + times.size());
        timesAdapter.addTimes(times);
    }

    @Override
    public void initializeViews(@NotNull List<Day> days, @NotNull Day selectedDay) {
        weekDayAdapter.setDays(days);
        weekDayAdapter.selectDay(selectedDay);
    }

    @Override
    public void showProgress() {
        timesProgressBar.setVisibility(View.VISIBLE);
        timeRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        timesProgressBar.setVisibility(View.GONE);
        timeRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void changeSelectedDay(@NotNull Day day) {
        Log.e("TimesFragment", "changeSelectedDay" + ": " + 1);
        weekDayAdapter.selectDay(day);
    }

    public void clearOldTimes() {
        timesAdapter.clearAll();
    }

    @Override
    public void onDestroyView() {
        if (timesPresenter != null) {
            timesPresenter.onDetach();
            timesAdapter = null;
            weekDayAdapter = null;
            timeRecyclerView = null;
            timesPresenter = null;
            timesProgressBar = null;
        }
        super.onDestroyView();
    }
}
