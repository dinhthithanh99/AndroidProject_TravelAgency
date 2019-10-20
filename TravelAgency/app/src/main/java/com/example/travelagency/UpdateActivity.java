package com.example.travelagency;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateActivity extends AppCompatActivity {
    AppDatabase db;
    EditText editTitle;
    EditText editPlace;
    EditText editTime;
    EditText editVehicle;
    Button btnUpdate;
    int tourId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").build();

        editTitle = findViewById(R.id.ed_Title);
        editPlace = findViewById(R.id.ed_Place);
        editTime = findViewById(R.id.ed_Time);
        editVehicle = findViewById(R.id.ed_Vehicle);

        btnUpdate = findViewById(R.id.bt_Update);

        int id = getIntent().getIntExtra("id", 0);
        tourId = id;

        editTitle.setText(getIntent().getStringExtra("title"));
        editPlace.setText(getIntent().getStringExtra("place"));
        editTime.setText(getIntent().getStringExtra("time"));
        editVehicle.setText(getIntent().getStringExtra("vehicle"));


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateDatabase();

            }
        });
    }

    private void updateDatabase() {
        final String title = editTitle.getText().toString();
        final String place = editPlace.getText().toString();
        final String time = editTime.getText().toString();
        final String vehicle = editVehicle.getText().toString();

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                travelTour travelTours = new travelTour();
                travelTours.setTitle(title);
                travelTours.setPlace(place);
                travelTours.setTime(time);
                travelTours.setVehicle(vehicle);
                travelTours.setId(tourId); // thinking about why we need to set id here
                db.tourDao().updateOne(travelTours);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                showSuccessDialog();
            }
        }.execute();
    }

    private void showSuccessDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Message")
                .setMessage("Update Successfully!")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .show();
    }
}
