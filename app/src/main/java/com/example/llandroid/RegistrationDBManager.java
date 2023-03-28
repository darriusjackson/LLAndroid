package com.example.llandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class RegistrationDBManager extends SQLiteOpenHelper
{
    // the name of the database created for the registration page
    private static final String dbname2="LinkedList.db";

    // database manager method containing the database and version of the database
    public RegistrationDBManager(Context context)
    {
        super(context, dbname2, null, 1);
    }

    // creates the registration table in the database
    @Override
    public void onCreate(SQLiteDatabase db2)
    {
        String qry="create table tbl_registration (id integer primary key autoincrement, fullName text, email text, phoneNumber text, password text, confirmPassword text)";
        db2.execSQL(qry);
    }

    // if the registration table exists then it will drop the old version and replace with an updated one
    @Override
    public void onUpgrade(SQLiteDatabase db2, int oldVersion, int newVersion)
    {
        db2.execSQL("DROP TABLE IF EXISTS tbl_registration");
        onCreate(db2);
    }

    // method used to the registration records into the SQLite database
    public String RegistrationAddDBRecord(String p1, String p2, String p3, String p4, String p5)
    {
        SQLiteDatabase db2=this.getWritableDatabase();

        ContentValues cv=new ContentValues();
        cv.put("fullName",p1);
        cv.put("email",p2);
        cv.put("phoneNumber",p3);
        cv.put("password", p4);
        cv.put("confirmPassword",p5);

        long res=db2.insert("tbl_registration",null,cv);

        if(res==-1)
            return "Failed";
        else
            return "Successfully inserted";
    }

    }





