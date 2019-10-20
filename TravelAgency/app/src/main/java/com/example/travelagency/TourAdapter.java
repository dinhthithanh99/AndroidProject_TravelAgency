package com.example.travelagency;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TourAdapter extends RecyclerView.Adapter<TourAdapter.TourViewHolder> {
    List<travelTour> travelTourList;
    private OnItemClicked onClick;

    public interface OnItemClicked {
        void onClickItemDelete(int position);

        void onClickItemUpdate(int position);
    }

    public void setOnClick(OnItemClicked onClick) {
        this.onClick = onClick;
    }

    public TourAdapter(Runnable mainActivity, List<travelTour> travelTours) {
        travelTourList = travelTours;
    }

    @NonNull
    @Override
    public TourViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_tour, parent, false);
        return new TourViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TourAdapter.TourViewHolder holder, final int position) {
        holder.tvName.setText(travelTourList.get(position).toString());
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClick.onClickItemDelete(position);

            }
        });

        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClick.onClickItemUpdate(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (travelTourList == null) {
            return 0;
        }
        return travelTourList.size();
    }

    public class TourViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        Button btnUpdate, btnDelete;

        public TourViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            btnUpdate = itemView.findViewById(R.id.updateTour);
            btnDelete = itemView.findViewById(R.id.disableTour);
        }
    }
}
