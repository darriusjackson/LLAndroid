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

public class EventAdapter extends ArrayAdapter<Event>
{
    // method for the event/task adapter
    public EventAdapter(@NonNull Context context, List<Event> events)
    {
        super(context, 0, events);
    }

    @NonNull
    @Override
    // sets up the new event/task view when the user clicks the New Event button
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        Event event = getItem(position);

        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_eventcell, parent, false);

        TextView eventCellTV = convertView.findViewById(R.id.eventCellApp);

        String eventTitle = event.getName() +" "+ CalendarUtilities.formattedTime(event.getTime());
        eventCellTV.setText(eventTitle);
        return convertView;
    }
}
