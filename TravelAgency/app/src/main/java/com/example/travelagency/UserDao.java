package com.example.travelagency;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insertAll(UserComment... userComments);

    @Query("SELECT * FROM UserComment")
    List<UserComment> getAll();

    @Update
    void updateOne(UserComment... userComments);

    @Query("DELETE FROM UserComment")
    void deleteAll();
}