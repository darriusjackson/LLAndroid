package com.example.llandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.time.LocalTime;

public class EventEdit extends AppCompatActivity {
    // variables used in the EventEdit code
    private EditText eventNameText;
    private TextView eventDateText, eventTimeText;

    private TaskDBManager db;
    private Button insertTask;
    private LocalTime time;

    // sets up the view of the event/task edit page, initializes methods, sets up attributes for the database manager
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventedit);
        initWidgets();
        time = LocalTime.now();
        eventDateText.setText("Date: " + CalendarUtilities.formattedDate(CalendarUtilities.selectedDate));
        eventTimeText.setText("Time: " + CalendarUtilities.formattedTime(time));
        insertTask = findViewById(R.id.taskBtn);
        db = new TaskDBManager(EventEdit.this);

    }

    // the variables are assigned to certain IDs using findViewById()
    private void initWidgets() {
        eventNameText = findViewById(R.id.eventNameText);
        eventDateText = findViewById(R.id.eventDateText);
        eventTimeText = findViewById(R.id.eventTimeText);

    }


    // saves the event/task the user created for that day and adds the task record into the SQLite database
    public void saveEventButton(View view) {

        String eventName = eventNameText.getText().toString();
        Event newEvent = new Event(eventName, CalendarUtilities.selectedDate, time);
        Event.eventsList.add(newEvent);

        if (eventName.isEmpty()){
            Toast.makeText(EventEdit.this, "Enter name",Toast.LENGTH_LONG).show();
            return;
        }

        db.TaskAddDBRecord(eventName);

        Toast.makeText(EventEdit.this, "Task added",Toast.LENGTH_LONG).show();
        eventNameText.setText("");

        finish();

    }


 }




