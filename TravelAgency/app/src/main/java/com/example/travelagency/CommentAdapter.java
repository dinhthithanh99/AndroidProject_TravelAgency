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

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {
    List<UserComment> travelTourList;

    public CommentAdapter(Runnable mainActivity, List<UserComment> travelTours) {
        travelTourList = travelTours;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_show_list, parent, false);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.CommentViewHolder holder, final int position) {
        holder.tvList.setText(travelTourList.get(position).toString());
    }

    @Override
    public int getItemCount() {
        if (travelTourList == null) {
            return 0;
        }
        return travelTourList.size();
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder {
        TextView tvList;


        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            tvList = itemView.findViewById(R.id.tvList);

        }
    }
}
