package ir.reservs.reservs.ui.main.times;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ir.reservs.reservs.R;
import ir.reservs.reservs.model.Day;

public class WeekDayAdapter extends RecyclerView.Adapter<WeekDayAdapter.ViewHolder> {
    private List<Day> days;
    private Day selectedDay = null;

    public WeekDayAdapter(List<Day> days) {
        this.days = days;
    }

    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.week_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtDayName.setText(days.get(position).getName() + "");
        holder.txtDate.setText(days.get(position).getNum() + "");
        if (days.get(position).equals(selectedDay)) {
            holder.txtDate = textSelectStyle(holder.txtDate);
        } else {
            holder.txtDate = textNormalStyle(holder.txtDate);
        }
    }

    private TextView textSelectStyle(TextView txt) {
        txt.setBackgroundResource(R.drawable.square_selected_bg);
        txt.setTextColor(Color.WHITE);
        return txt;
    }

    private TextView textNormalStyle(TextView txt) {
        txt.setBackgroundResource(R.drawable.square_bg);
        txt.setTextColor(Color.GRAY);
        return txt;
    }

    @Override
    public int getItemCount() {
        return days.size();
    }


    public void setDays(List<Day> days) {
        this.days.addAll(days);
        notifyDataSetChanged();
    }

    public void selectDay(Day day) {
        this.selectedDay = day;
        notifyDataSetChanged();
        //notifyItemChanged(days.indexOf(selectedDay));
    }

    public Day getSelectedDay() {
        return selectedDay;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtDayName, txtDate;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDayName = itemView.findViewById(R.id.txtDayName);
            txtDate = itemView.findViewById(R.id.txtDate);
        }
    }
}
