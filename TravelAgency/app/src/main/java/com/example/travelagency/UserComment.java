package com.example.travelagency;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class UserComment {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String title;
    public String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "- Comment of user:" +
                "\n\t+ Title:\t " + title +
                "\n\t+ Content:\t " + content;
    }
}