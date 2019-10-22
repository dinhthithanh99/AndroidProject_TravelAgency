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

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {
    List<travelTour> travelTourList;
    private OnItemClicked onClick;

    public interface OnItemClicked {
        void onClickItemAddCart(int position);
    }

    public void setOnClick(OnItemClicked onClick) {
        this.onClick = onClick;
    }

    public ListAdapter(Runnable mainActivity, List<travelTour> travelTours) {
        travelTourList = travelTours;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_show_list, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ListViewHolder holder, final int position) {
        holder.tvList.setText(travelTourList.get(position).toString());
        holder.btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClick.onClickItemAddCart(position);

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

    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView tvList;
        Button btnAddCart;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            tvList = itemView.findViewById(R.id.tvList);
            btnAddCart = itemView.findViewById(R.id.addCart);


        }
    }
}
