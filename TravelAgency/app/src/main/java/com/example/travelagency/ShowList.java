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
import android.widget.Toast;

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
        findViewById(R.id.showTour).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowList.this, ShowCart.class);
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

    private void bookTour(final travelTour tours) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                Booktour bookTours = new Booktour();
                bookTours.setProdId(tours.getId());
                bookTours.setTitle(tours.getTitle());
                bookTours.setPlace(tours.getPlace());
                bookTours.setTime(tours.getTime());
                bookTours.setVehicle(tours.getVehicle());
                db.BookTourDao().insertAll(bookTours);
                return null;
            }
        }.execute();
    }

    @Override
    public void onClickItemAddCart(int position) {
        bookTour(travelTours.get(position));
        Toast.makeText(ShowList.this, "Book tour successfully!", Toast.LENGTH_SHORT).show();
    }

}


