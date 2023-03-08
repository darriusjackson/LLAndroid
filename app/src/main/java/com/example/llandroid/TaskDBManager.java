package com.example.llandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class TaskDBManager extends SQLiteOpenHelper
{
    // the name of the database created for the new event/task page
    private static final String dbname3="LinkedListTA.db";

    // database manager method containing the database and version of the database
    public TaskDBManager(Context context)
    {
        super(context, dbname3, null, 1);
    }

    // creates the tasks table in the database
    @Override
    public void onCreate(SQLiteDatabase db3)
    {
        String qry="create table tbl_tasks (id integer primary key autoincrement, eventName text)";
        db3.execSQL(qry);
    }

    // if the tasks table exists then it will drop the old version and replace with an updated one
    @Override
    public void onUpgrade(SQLiteDatabase db3, int oldVersion, int newVersion)
    {
        db3.execSQL("DROP TABLE IF EXISTS tbl_tasks");
        onCreate(db3);
    }

    // method used to the tasks records into the SQLite database
    public String TaskAddDBRecord(String p1)
    {
        SQLiteDatabase db3=this.getWritableDatabase();

        ContentValues cv=new ContentValues();
        cv.put("eventName",p1);


        long res=db3.insert("tbl_tasks",null,cv);

        if(res==-1)
            return "Failed";
        else
            return "Successfully inserted";
    }
}

