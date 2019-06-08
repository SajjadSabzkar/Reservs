package ir.reservs.reservs.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ir.reservs.reservs.model.Salon;


/**
 * Created by mhrezai on 08/10/2018.
 */

public class SalonAdapter extends RecyclerView.Adapter<SalonAdapter.SalonViewHolder> {
    private List<Salon> data = new ArrayList<>();
    private Picasso picasso;


    public void setData(List<Salon> data) {
        this.data = data;
        this.notifyDataSetChanged();
    }


    @NonNull
    @Override
    public SalonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull SalonViewHolder holder, final int position) {
        //Picasso.get().load(data.get(position).getThumbnail()).into(holder.imgThumbnail);
        picasso.load(data.get(position).getThumbnail())
                .resize(270, 270)
                .into(holder.imgThumbnail);
        holder.txtTitle.setText(data.get(position).getTitle());
        holder.txtLocation.setText(data.get(position).getDescription());
        holder.llClickable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class SalonViewHolder extends RecyclerView.ViewHolder {
        ImageView imgThumbnail;
        TextView txtTitle, txtLocation;
        LinearLayout llClickable;

        public SalonViewHolder(View itemView) {
            super(itemView);
        }
    }
}
