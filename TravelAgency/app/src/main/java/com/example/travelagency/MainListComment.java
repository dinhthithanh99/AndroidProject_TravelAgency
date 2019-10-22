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

public class MainListComment extends AppCompatActivity {
    public RecyclerView recyclerViews;
    public AppDatabase db;
    public static List<UserComment> travelTours;
    public CommentAdapter listAdapters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list_comment);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        recyclerViews = findViewById(R.id.recycleviewList);
        recyclerViews.setLayoutManager(new LinearLayoutManager(this));

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").build();

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainListComment.this, AdminManagement.class);
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
                travelTours = db.UserDao().getAll();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        listAdapters = new CommentAdapter(this, travelTours);
                        recyclerViews.setAdapter(listAdapters);
                    }
                });
                return null;
            }
        }.execute();
    }
}


