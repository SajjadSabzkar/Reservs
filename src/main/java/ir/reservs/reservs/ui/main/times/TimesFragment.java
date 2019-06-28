package ir.reservs.reservs.ui.main.times;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import ir.huri.jcal.JalaliCalendar;
import ir.reservs.reservs.R;
import ir.reservs.reservs.model.Day;
import ir.reservs.reservs.ui.base.BaseFragment;

public class TimesFragment extends BaseFragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_times, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<Day> days = new ArrayList<>();
        JalaliCalendar jalaliDate = new JalaliCalendar(new GregorianCalendar());
        for (int i = 0; i < 7; i++) {
            String firstLetter = jalaliDate.getDayOfWeekString().substring(0, 1);
            int dayOfWeek = jalaliDate.getDay();
            days.add(new Day(dayOfWeek, firstLetter));
            jalaliDate = jalaliDate.getTomorrow();
        }
        WeekDayAdapter adapter = new WeekDayAdapter(days);
        adapter.selectDay(days.get(0));
        RecyclerView dayRecyclerView = view.findViewById(R.id.dayRecyclerView);
        dayRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onError(String msg) {

    }

    @Override
    public void setup(View view) {

    }
}
