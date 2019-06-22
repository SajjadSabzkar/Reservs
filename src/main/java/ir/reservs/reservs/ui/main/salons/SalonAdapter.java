package ir.reservs.reservs.ui.main.salons;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ir.reservs.reservs.R;
import ir.reservs.reservs.model.Salon;

public class SalonAdapter extends RecyclerView.Adapter<SalonAdapter.Holder> {

    private List<Salon> mData;

    public SalonAdapter(ArrayList<Salon> mData) {
        this.mData = mData;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_salon, parent, false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        // Salon salon = mData.get(position);
        Picasso.get()
                .load(mData.get(position).getThumbnail())
                .resize(136, 136)
                .error(R.drawable.avatar)
                .into(holder.imgThumbnail);

        holder.txtLocation.setText(mData.get(position).getTitle());
        holder.txtCity.setText(mData.get(position).getCityName());
        holder.txtPrice.setText(
                String.format("از %s تا %s تومان",
                        mData.get(position).getMinPrice(),
                        mData.get(position).getMaxPrice()));

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    void addItems(List<Salon> salonList) {
        mData.addAll(salonList);
        notifyDataSetChanged();
    }


    class Holder extends RecyclerView.ViewHolder {
        ImageView imgThumbnail;
        TextView txtCity;
        TextView txtLocation;
        TextView txtPrice;

        Holder(@NonNull View itemView) {
            super(itemView);
            imgThumbnail = itemView.findViewById(R.id.imgThumbnail);
            txtCity = itemView.findViewById(R.id.txtCityName);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            txtLocation = itemView.findViewById(R.id.txtLocation);
        }
    }
}

