package com.example.travelagency;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import java.util.List;

public class ShowList extends AppCompatActivity implements ListAdapter.OnItemClicked {
    public RecyclerView recyclerViews;
    public AppDatabase db;
    public static List<travelTour> travelTours;
    public ListAdapter listAdapters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        recyclerViews = findViewById(R.id.recycleviewList);
        recyclerViews.setLayoutManager(new LinearLayoutManager(this));

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").build();

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowList.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void onResume() {
        super.onResume();
        getTour();
    }

    public void getTour() {
        new AsyncTask<Void, Void, List<travelTour>>() {
            @Override
            protected List<travelTour> doInBackground(Void... voids) {
                travelTours = db.tourDao().getAll();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        listAdapters = new ListAdapter(this, travelTours);
                        listAdapters.setOnClick(ShowList.this);
                        recyclerViews.setAdapter(listAdapters);
                    }
                });
                return null;
            }
        }.execute();
    }

    private void openAddtoCartScreen(travelTour tours) {
        Intent intent = new Intent(ShowList.this, UpdateActivity.class);
        intent.putExtra("id", tours.getId());
        intent.putExtra("title", tours.getTitle());
        intent.putExtra("place", tours.getPlace());
        intent.putExtra("time", tours.getTime());
        intent.putExtra("vehicle", tours.getVehicle());
        startActivity(intent);
    }

    @Override
    public void onClickItemAddCart(int position) {

    }

}


