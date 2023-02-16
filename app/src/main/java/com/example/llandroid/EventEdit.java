package com.example.llandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.time.LocalTime;

public class EventEdit extends AppCompatActivity
{
    private EditText eventNameText;
    private TextView eventDateText, eventTimeText;

    private LocalTime time;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventedit);
        initWidgets();
        time = LocalTime.now();
        eventDateText.setText("Date: " + CalendarUtilities.formattedDate(CalendarUtilities.selectedDate));
        eventTimeText.setText("Time: " + CalendarUtilities.formattedTime(time));
    }

    private void initWidgets()
    {
        eventNameText = findViewById(R.id.eventNameText);
        eventDateText = findViewById(R.id.eventDateText);
        eventTimeText = findViewById(R.id.eventTimeText);
    }

    public void saveEventAction(View view)
    {
        String eventName = eventNameText.getText().toString();
        Event newEvent = new Event(eventName, CalendarUtilities.selectedDate, time);
        Event.eventsList.add(newEvent);
        finish();
    }
}