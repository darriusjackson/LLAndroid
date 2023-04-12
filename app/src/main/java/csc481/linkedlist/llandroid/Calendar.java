package csc481.linkedlist.llandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
//import android.widget.Toast;

import java.time.LocalDate;
//import java.time.YearMonth;
//import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Calendar extends AppCompatActivity implements CalWeekAdapter.OnItemListener
{
    // variables for the month year and calendar recycler view
    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;

// sets the monthly calendar view and initializes methods that will be used in the code
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        initWidgets();
        CalendarUtilities.selectedDate = LocalDate.now();
        setMonthView();

        // variables used for the bottom navigation method
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.settings);

        // method used to get the bottom navigation to work
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.calender:
                        return true;
                    case R.id.settings:
                        startActivity(new Intent(Calendar.this,SettingsActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.notes:
                        startActivity(new Intent(Calendar.this,NotesActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(Calendar.this,ProfilePage.class));
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });

    }
// the variables are assigned to certain IDs using the findViewById()
    private void initWidgets()
    {
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        monthYearText = findViewById(R.id.monthYearApp);
    }

// sets up the month view of the calendar
    private void setMonthView()
    {
        monthYearText.setText(CalendarUtilities.monthYearFromDate(CalendarUtilities.selectedDate));
        ArrayList<LocalDate> daysInMonth = CalendarUtilities.daysInMonthArray(CalendarUtilities.selectedDate);

        CalWeekAdapter calendarAdapter = new CalWeekAdapter(daysInMonth, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
    }
// sets up the previous month button (or Back button)
    public void previousMonthAction(View view)
    {
        CalendarUtilities.selectedDate = CalendarUtilities.selectedDate.minusMonths(1);
        setMonthView();
    }
// sets up the next month button
    public void nextMonthAction(View view)
    {
        CalendarUtilities.selectedDate = CalendarUtilities.selectedDate.plusMonths(1);
        setMonthView();
    }

    // this method is used to show the marker of the current date in the monthly calendar
    @Override
    public void onItemClick(int position, LocalDate date)
    {
        if(date != null)
        {
            CalendarUtilities.selectedDate = date;
            setMonthView();
        }
    }
// sets up the add task button
    public void taskAction(View view)
    {
        startActivity(new Intent(Calendar.this, EventEdit.class));

    }

    // sets up the weekly calendar button
    public void weeklyAction(View view)
    {
        startActivity(new Intent(Calendar.this, WeekView.class));

    }
}

