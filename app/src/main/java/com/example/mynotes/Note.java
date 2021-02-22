package com.example.mynotes;

public class Note {
    String text;
    java.sql.Timestamp date;

    public Note(String text, java.sql.Timestamp date) {
        this.text = text;
        this.date = date;
    }
}
