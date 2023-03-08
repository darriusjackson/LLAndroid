package com.example.llandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class LogInDBManager extends SQLiteOpenHelper
{
    // the name of the database created for the log in page
    private static final String dbname="LinkedList.db";

    // database manager method containing the database and version of the database
    public LogInDBManager(Context context)
    {
        super(context, dbname, null, 1);
    }

    @Override
    // creates the login table in the database
    public void onCreate(SQLiteDatabase db)
    {
        String qry="create table tbl_login (id integer primary key autoincrement, email text, password text)";
        db.execSQL(qry);
    }

    @Override
    // if the login table exists then it will drop the old version and replace with an updated one
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS tbl_login");
        onCreate(db);
    }

    // method used to the login records into the SQLite database
    public String LogInAddDBRecord(String p1, String p2)
    {
        SQLiteDatabase db1=this.getWritableDatabase();

        ContentValues cv=new ContentValues();
        cv.put("email",p1);
        cv.put("password", p2);

        long res=db1.insert("tbl_login",null,cv);

        if(res==-1)
            return "Failed";
        else
            return "Successfully inserted";
    }
}

