package com.example.mynotes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    final static String DB_NAME = "notes.db";
    final static String TABLE_NAME = "notes";
    final static String CREATE = "CREATE TABLE "+TABLE_NAME+ "( `_id` INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "`note` TEXT NOT NULL, `date` DATETIME NOT NULL)";

    private static final int DATABASE_VERSION = 10;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // выполняется, если базы данных нет
        db.execSQL(CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // выполняется, если версия базы данных отличается
        db.execSQL("DROP DATABASE "+DB_NAME);
        this.onCreate(db);
    }
}
