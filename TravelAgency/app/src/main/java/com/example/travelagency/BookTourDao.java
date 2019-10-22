package com.example.travelagency;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BookTourDao {
    @Insert
    void insertAll(Booktour... Booktours);

    @Query("SELECT * FROM Booktour")
    List<Booktour> getAll();

    @Update
    void updateOne(Booktour... Booktours);

    @Query("DELETE FROM Booktour")
    void deleteAll();

    @Delete
    void deleteOne(Booktour... booktours);
}