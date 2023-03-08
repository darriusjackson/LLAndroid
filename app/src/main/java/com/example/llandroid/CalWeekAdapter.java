package com.example.llandroid;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.ArrayList;

class CalWeekAdapter extends RecyclerView.Adapter<CalViewHolder>
{
    // variables for the calendar week adapter
    private final ArrayList<LocalDate> days;
    private final OnItemListener onItemListener;

    // method for the calendar week adapter
    public CalWeekAdapter(ArrayList<LocalDate> days, OnItemListener onItemListener)
    {
        this.days = days;
        this.onItemListener = onItemListener;
    }

    // calls a method from CalViewHolder to adapt the days into the days of the week and month

    @NonNull
    @Override
    public CalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.activity_calendarcell, parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if(days.size() > 15) //month view
            layoutParams.height = (int) (parent.getHeight() * 0.166666666);
        else // week view
            layoutParams.height = parent.getHeight();

        return new CalViewHolder(view, onItemListener, days);
    }

    // calls a second method from CalViewHolder to adapt the days into the days of the month
    @Override
    public void onBindViewHolder(@NonNull CalViewHolder holder, int position)
    {
        final LocalDate date = days.get(position);
        if(date == null)
            holder.dayOfMonth.setText("");
        else
        {
            holder.dayOfMonth.setText(String.valueOf(date.getDayOfMonth()));
            if(date.equals(CalendarUtilities.selectedDate))
                holder.parentView.setBackgroundColor(Color.LTGRAY);
        }
    }

    // gets the number of days for the weeek
    @Override
    public int getItemCount()
    {
        return days.size();
    }

    // public interface that was used to set up the month and week views of the calendar
    public interface  OnItemListener
    {
        void onItemClick(int position, LocalDate date);
    }
}