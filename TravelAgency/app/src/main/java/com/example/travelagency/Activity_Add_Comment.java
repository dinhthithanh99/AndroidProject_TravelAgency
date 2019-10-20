package com.example.travelagency;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.DialogInterface;
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

public class Activity_Add_Comment extends AppCompatActivity {
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__add__comment);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").build();


        findViewById(R.id.bt_addComment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addComment();
                showSuccessDialog();
                finish();
            }
        });
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Add_Comment.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void addComment() {

        EditText editTextTitle = (EditText) findViewById(R.id.title);
        final String title = editTextTitle.getText().toString();
        EditText editTextContent = (EditText) findViewById(R.id.content);
        final String content = editTextContent.getText().toString();

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                UserComment userComments = new UserComment();
                userComments.setTitle(title);
                userComments.setContent(content);
                db.UserDao().insertAll(userComments);
                return null;
            }
        }.execute();
    }

    private void showSuccessDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Message")
                .setMessage("You sent a feedback Successfully!")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .show();
    }
}
