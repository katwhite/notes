package com.example.mynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.sql.StatementEvent;

public class MainActivity extends AppCompatActivity {

    NoteListAdapter adapter;
    ListView listView;
    SQLiteDatabase db;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView count = findViewById(R.id.count);
        listView = findViewById(R.id.list);
        ArrayList<Note> notes = new ArrayList<>();
        dbHelper = new DBHelper(this);
        db = dbHelper.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM notes ORDER by _id DESC", null);

        while (c.moveToNext()) {
            String name = c.getString(c.getColumnIndex("note"));
            java.sql.Timestamp date = Timestamp.valueOf(c.getString(c.getColumnIndex("date")));
            notes.add(new Note(name, date));
        }
        count.setText("number of Notes: "+notes.size());
        c.close();

        adapter = new NoteListAdapter(this, notes);

        listView.setAdapter(adapter);

        Button btnRestart = findViewById(R.id.button);
        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edit = findViewById(R.id.text);
                String text = edit.getText().toString();
                if (text.isEmpty()) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Text is empty!", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }
                Time now = new Time();
                now.setToNow();
                java.sql.Timestamp param = new java.sql.Timestamp(now.toMillis(true));
                ContentValues values = new ContentValues();
                values.put("note", text);
                values.put("date", param.toString());
                long newRowId = db.insert("notes", null, values);
                notes.add(new Note(text, param));
                adapter.notifyDataSetChanged();
                count.setText("number of Notes: "+notes.size());
            }
        });

    }

}