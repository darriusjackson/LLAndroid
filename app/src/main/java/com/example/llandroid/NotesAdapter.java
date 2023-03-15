package com.example.llandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class NotesAdapter extends ArrayAdapter<Notes>
{
    // method to set up the notes adpater in the code
    public NotesAdapter(Context context, List<Notes> notes)
    {
        super(context, 0, notes);
    }

    // method to get the view of the notes details activity using the adapter
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        Notes note = getItem(position);
        if(convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.notes_cell, parent, false);

        TextView title = convertView.findViewById(R.id.cellTitle);
        TextView desc = convertView.findViewById(R.id.cellDesc);

        title.setText(note.getTitle());
        desc.setText(note.getDescription());

        return convertView;
    }
}
