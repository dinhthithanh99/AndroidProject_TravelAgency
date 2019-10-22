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

public class ShowCart extends AppCompatActivity implements ShowCartAdapter.OnItemClicked {
    public RecyclerView recyclerViews;
    public AppDatabase db;
    public static List<Booktour> booktours;
    public ShowCartAdapter showCartAdapters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_cart);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        recyclerViews = findViewById(R.id.recycleviewList);
        recyclerViews.setLayoutManager(new LinearLayoutManager(this));

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").build();

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowCart.this, ShowList.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAllCart();
            }
        });

    }

    public void deleteAllCart(){
        new AlertDialog.Builder(this)
                .setTitle("Confirm")
                .setMessage("Are you sure?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(ShowCart.this, booktours.toString() + " has been deleted", Toast.LENGTH_SHORT).show();
                        new AsyncTask<Void, Void, Void>() {
                            @Override
                            protected Void doInBackground(Void... voids) {
                                db.BookTourDao().deleteAll();
                                return null;
                            }

                            @Override
                            protected void onPostExecute(Void aVoid) {
                                super.onPostExecute(aVoid);
                                showCartAdapters.BooktourList.removeAll(booktours);
                                showCartAdapters.notifyDataSetChanged();


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

    public void onResume() {
        super.onResume();
        getTour();
    }

    public void getTour() {
        new AsyncTask<Void, Void, List<travelTour>>() {
            @Override
            protected List<travelTour> doInBackground(Void... voids) {
                booktours = db.BookTourDao().getAll();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showCartAdapters = new ShowCartAdapter(this, booktours);
                        showCartAdapters.setOnClick(ShowCart.this);
                        recyclerViews.setAdapter(showCartAdapters);
                    }
                });
                return null;
            }
        }.execute();
    }


    @Override
    public void onClickItemDeleteOne(final int position) {
        showAlertDelete(position);
    }

    private void showAlertDelete(final int position) {
        new AlertDialog.Builder(this)
                .setTitle("Confirm")
                .setMessage("Are you sure?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(ShowCart.this, booktours.get(position).toString() + " has been deleted", Toast.LENGTH_SHORT).show();
                        new AsyncTask<Void, Void, Void>() {
                            @Override
                            protected Void doInBackground(Void... voids) {
                                db.BookTourDao().deleteOne(booktours.get(position));
                                return null;
                            }

                            @Override
                            protected void onPostExecute(Void aVoid) {
                                super.onPostExecute(aVoid);
                                showCartAdapters.BooktourList.remove(position);
                                showCartAdapters.notifyDataSetChanged();


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


