package com.example.mynotes;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

public class NoteListAdapter extends BaseAdapter {
    Context ctx; ArrayList<Note> notes;

    public NoteListAdapter(Context ctx, ArrayList<Note> notes) {
        this.ctx = ctx;
        this.notes = notes;
    }

    @Override
    public int getCount() {
        return notes.size();
    }

    @Override
    public Object getItem(int position) {
        return notes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // получаем данные из коллекции
        Date begin = new Date();
        Note u = notes.get(position);

        // создаём разметку (контейнер)
        convertView = LayoutInflater.from(ctx).
                inflate(R.layout.item, parent, false);

        TextView tvText = convertView.findViewById(R.id.text);
        TextView tvDate = convertView.findViewById(R.id.date);

        // задаём содержание
        tvText.setText(u.text);
        tvDate.setText(u.date.toString());

        return convertView;
    }
}
