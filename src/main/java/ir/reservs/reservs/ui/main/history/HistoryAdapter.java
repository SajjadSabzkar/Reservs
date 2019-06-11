package ir.reservs.reservs.ui.main.history;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ir.reservs.reservs.R;
import ir.reservs.reservs.model.ReserveHistory;
import ir.reservs.reservs.utils.CommonUtils;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.Holder> {

    private List<ReserveHistory> mData;

    public HistoryAdapter(ArrayList<ReserveHistory> mData) {
        this.mData = mData;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_history, parent, false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        ReserveHistory reserveHistory = mData.get(position);
        holder.txtName.setText(reserveHistory.getName());
        holder.txtTime.setText(reserveHistory.getStartTime());
        holder.txtTime.append(" الی ");
        holder.txtTime.append(reserveHistory.getEndTime());
        holder.txtDate.setText(CommonUtils.dateFormatStandard(reserveHistory.getDate()));
        holder.txtLocation.setText(reserveHistory.getLocation());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    void addItems(List<ReserveHistory> reserveHistories) {
        mData.addAll(reserveHistories);
        notifyDataSetChanged();
    }


    class Holder extends RecyclerView.ViewHolder {
        TextView txtName;
        TextView txtTime;
        TextView txtDate;
        TextView txtLocation;

        Holder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtTime = itemView.findViewById(R.id.txtTime);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtLocation = itemView.findViewById(R.id.txtLocation);
        }
    }
}
