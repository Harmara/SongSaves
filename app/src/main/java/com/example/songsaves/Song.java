package com.example.songsaves;

import androidx.annotation.NonNull;

public class Song {
    private int id;
    private String title;
    private String singer;
    private int year;
    private int rating;

    public Song(int id, String title, String singer, int year, int rating) {
        this.id = id;
        this.title = title;
        this.singer = singer;
        this.year = year;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSinger() {
        return singer;
    }

    public int getYear() {
        return year;
    }

    public int getRating() {
        return rating;
    }

    @NonNull
    @Override
    public String toString() {
        return "ID: " + id + "\nTitle: " + title + "\nSinger: " + singer + "\nYear: " + year + "\nRating: " + rating;
    }
}

