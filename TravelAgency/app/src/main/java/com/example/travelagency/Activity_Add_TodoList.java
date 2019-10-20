package com.example.travelagency;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.travelagency.AppDatabase;
import com.example.travelagency.R;
import com.example.travelagency.travelTour;

import java.util.List;

public class Activity_Add_TodoList extends AppCompatActivity {
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__add__todo_list);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").build();


        findViewById(R.id.bt_addData).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTour();
                finish();
            }
        });
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Add_TodoList.this,Main2Activity.class);
                startActivity(intent);
            }
        });
    }

    public void addTour() {

        EditText editTextTitle = (EditText) findViewById(R.id.title);
        final String title = editTextTitle.getText().toString();
        EditText editTextPlace = (EditText) findViewById(R.id.place);
        final String place = editTextPlace.getText().toString();
        EditText editTextTime = (EditText) findViewById(R.id.timeday);
        final String time = editTextTime.getText().toString();
        EditText editTextVehicle = (EditText) findViewById(R.id.vehicle);
        final String vehicle = editTextVehicle.getText().toString();

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                travelTour travelTour = new travelTour();
                travelTour.setTitle(title);
                travelTour.setPlace(place);
                travelTour.setTime(time);
                travelTour.setVehicle(vehicle);
                db.tourDao().insertAll(travelTour);
                return null;
            }
        }.execute();
    }
}
