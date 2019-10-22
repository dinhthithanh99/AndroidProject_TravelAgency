package com.example.travelagency;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {travelTour.class, UserComment.class,Booktour.class}, version = 3, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TourDao tourDao();

    public abstract UserDao UserDao();

    public abstract BookTourDao BookTourDao();

}