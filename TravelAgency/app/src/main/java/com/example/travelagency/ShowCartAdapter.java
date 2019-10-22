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

public class ShowCartAdapter extends RecyclerView.Adapter<ShowCartAdapter.ShowCartViewHolder> {
    List<Booktour> BooktourList;
    private OnItemClicked onClick;

    public interface OnItemClicked {
        void onClickItemDeleteOne(int position);

    }

    public void setOnClick(OnItemClicked onClick) {
        this.onClick = onClick;
    }

    public ShowCartAdapter(Runnable mainActivity, List<Booktour> Booktours) {
        BooktourList = Booktours;
    }

    @NonNull
    @Override
    public ShowCartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_show_cart, parent, false);
        return new ShowCartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowCartAdapter.ShowCartViewHolder holder, final int position) {
        holder.tvList.setText(BooktourList.get(position).toString());
        holder.btnDeleteOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClick.onClickItemDeleteOne(position);

            }
        });

    }

    @Override
    public int getItemCount() {
        if (BooktourList == null) {
            return 0;
        }
        return BooktourList.size();
    }

    public class ShowCartViewHolder extends RecyclerView.ViewHolder {
        TextView tvList;
        Button btnDeleteOne;

        public ShowCartViewHolder(@NonNull View itemView) {
            super(itemView);
            tvList = itemView.findViewById(R.id.tvList);
            btnDeleteOne = itemView.findViewById(R.id.deleteOne);

        }
    }
}
