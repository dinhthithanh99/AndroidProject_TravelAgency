package com.example.travelagency;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TourDao {
    @Insert
    void insertAll(travelTour... travelTours);

    @Query("SELECT * FROM travelTour")
    List<travelTour> getAll();

    @Update
    void updateOne(travelTour... tours);

    @Query("DELETE FROM travelTour")
    void deleteAll();

    @Delete
    void deleteOne(travelTour... tours);
}