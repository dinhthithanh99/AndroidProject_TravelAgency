package com.example.travelagency;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class Main2Activity extends AppCompatActivity implements TourAdapter.OnItemClicked {
    public RecyclerView recyclerViewUser;
    public AppDatabase db;
    public static List<travelTour> travelTours;
    public TourAdapter tourAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        recyclerViewUser = findViewById(R.id.recycleview);
        recyclerViewUser.setLayoutManager(new LinearLayoutManager(this));

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").build();

        findViewById(R.id.add_tour).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Main2Activity.this, "click", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Main2Activity.this, Activity_Add_TodoList.class);
                startActivity(intent);
                tourAdapter.notifyDataSetChanged();
            }
        });
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this,AdminManagement.class);
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
                        tourAdapter = new TourAdapter(this, travelTours);
                        tourAdapter.setOnClick(Main2Activity.this);
                        recyclerViewUser.setAdapter(tourAdapter);
                    }
                });
                return null;
            }
        }.execute();
    }

    private void openUpdateTodoScreen(travelTour tours) {
        Intent intent = new Intent(Main2Activity.this, UpdateActivity.class);
        intent.putExtra("id", tours.getId());
        intent.putExtra("title", tours.getTitle());
        intent.putExtra("place", tours.getPlace());
        intent.putExtra("time", tours.getTime());
        intent.putExtra("vehicle", tours.getVehicle());
        startActivity(intent);
    }

    @Override
    public void onClickItemDelete(int position) {
        showAlertDelete(position);
    }

    @Override
    public void onClickItemUpdate(int position) {
        openUpdateTodoScreen(travelTours.get(position));
    }

    private void showAlertDelete(final int position) {
        new AlertDialog.Builder(this)
                .setTitle("Confirm")
                .setMessage("Are you sure?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(Main2Activity.this, travelTours.get(position).toString() + " has been deleted", Toast.LENGTH_SHORT).show();
                        new AsyncTask<Void, Void, Void>() {
                            @Override
                            protected Void doInBackground(Void... voids) {
                                db.tourDao().deleteOne(travelTours.get(position));
                                return null;
                            }

                            @Override
                            protected void onPostExecute(Void aVoid) {
                                super.onPostExecute(aVoid);
                                tourAdapter.travelTourList.remove(position);
                                tourAdapter.notifyDataSetChanged();


                            }

                        }.execute();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                })
                .show();
    }

}


