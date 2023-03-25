package com.example.llandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.util.ArrayList;

import static com.example.llandroid.CalendarUtilities.daysInMonthArray;
import static com.example.llandroid.CalendarUtilities.daysInWeekArray;
import static com.example.llandroid.CalendarUtilities.monthYearFromDate;

public class WeekView extends AppCompatActivity implements CalWeekAdapter.OnItemListener
{
    // variables for the month year,calendar recycler view< and events list
    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private ListView eventListView;

    // sets the weekly calendar view and initializes methods that will be used in the code
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekview);
        initWidgets();
        setWeekView();
    }

    // the variables are assigned to certain IDs using the findViewById()
    private void initWidgets()
    {
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        monthYearText = findViewById(R.id.monthYearApp);
        eventListView = findViewById(R.id.eventListView);
    }

    // sets up the week view of the calendar
    private void setWeekView()
    {
        monthYearText.setText(monthYearFromDate(CalendarUtilities.selectedDate));
        ArrayList<LocalDate> days = daysInWeekArray(CalendarUtilities.selectedDate);

        CalWeekAdapter calendarWeekAdapter = new CalWeekAdapter(days, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarWeekAdapter);
        setEventAdapter();
    }


    // sets up the previous week button (or Back button)
    public void previousWeekAction(View view)
    {
        CalendarUtilities.selectedDate = CalendarUtilities.selectedDate.minusWeeks(1);
        setWeekView();
    }

    // sets up the next week button
    public void nextWeekAction(View view)
    {
        CalendarUtilities.selectedDate = CalendarUtilities.selectedDate.plusWeeks(1);
        setWeekView();
    }

    // this method is used to show the marker of the current date in the weekly calendar
    @Override
    public void onItemClick(int position, LocalDate date)
    {
        CalendarUtilities.selectedDate = date;
        setWeekView();
    }

    // initializes the event adapter
    @Override
    protected void onResume()
    {
        super.onResume();
        setEventAdapter();
    }
// method used to set up the event adapter
    private void setEventAdapter()
    {
        ArrayList<Event> dailyEvents = Event.eventsForDate(CalendarUtilities.selectedDate);
        EventAdapter eventAdapter = new EventAdapter(getApplicationContext(), dailyEvents);
        eventListView.setAdapter(eventAdapter);
    }
// sets up the new event/task button
    public void newEventAction(View view)
    {
        startActivity(new Intent(WeekView.this, EventEdit.class));
    }

    // takes the user back to the monthly view
    public void monthlyViewAction(View view)
    {
        startActivity(new Intent(WeekView.this, Calendar.class));
    }

}
