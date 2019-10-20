package com.example.travelagency;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class travelTour {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String title;
    public String place;
    public String time;
    public String vehicle;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Override
    public String toString() {
        return "- Travel Tour:" +
                "\n\t+ Title:\t " + title +
                "\n\t+ Place of Departure:\t " + place +
                "\n\t+ Time to stay:\t " + time +
                "\n\t+ Transport:\t " + vehicle;
    }
}