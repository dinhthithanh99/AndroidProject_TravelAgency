package com.example.travelagency;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {travelTour.class, UserComment.class}, version = 2, exportSchema = true)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TourDao tourDao();

    public abstract UserDao UserDao();

}