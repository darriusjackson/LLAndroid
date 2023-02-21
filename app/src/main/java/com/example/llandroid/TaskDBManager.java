package com.example.llandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class TaskDBManager extends SQLiteOpenHelper
{
    private static final String dbname3="LinkedListTA.db";

    public TaskDBManager(Context context)
    {
        super(context, dbname3, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db3)
    {
        String qry="create table tbl_tasks (id integer primary key autoincrement, eventName text)";
        db3.execSQL(qry);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db3, int oldVersion, int newVersion)
    {
        db3.execSQL("DROP TABLE IF EXISTS tbl_tasks");
        onCreate(db3);
    }

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

