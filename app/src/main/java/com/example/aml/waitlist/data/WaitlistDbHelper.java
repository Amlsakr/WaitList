package com.example.aml.waitlist.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by aml on 10/04/18.
 */

public class WaitlistDbHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "waitlist.db";
    private static final int DATABASE_VERSION = 1 ;

    public WaitlistDbHelper(Context context) {
      super(context , DATABASE_NAME , null , DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

//        // Create a table to hold waitlistdata
//        final String SQL_CREATE_WAITLIST_TABLE = "CREATE TABLE  " + WaitlistContract.WaitlistEntry.TABLE_NAME
//                +"(" + WaitlistContract.WaitlistEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
//                WaitlistContract.WaitlistEntry.COLUMN_GUEST_NAME +" TEXT NOT NULL, " +
//                WaitlistContract.WaitlistEntry.COLUMN_PARTY_SIZE + " INTEGER NOT NULL, " +
//                WaitlistContract.WaitlistEntry.COLUMN_TIMESTAMP + "  TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
//                ") ; " ;


        final String SQL_CREATE_WAITLIST_TABLE = "CREATE TABLE " + WaitlistContract.WaitlistEntry.TABLE_NAME + " (" +
                WaitlistContract.WaitlistEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                WaitlistContract.WaitlistEntry.COLUMN_GUEST_NAME + " TEXT NOT NULL, " +
                WaitlistContract.WaitlistEntry.COLUMN_PARTY_SIZE + " INTEGER NOT NULL, " +
                WaitlistContract.WaitlistEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                "); ";
        //Execute the query by calling execSQL on sqliteDatabase and pass the string query SQL_CREATE_WAITLIST_TABLE
        db.execSQL(SQL_CREATE_WAITLIST_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + WaitlistContract.WaitlistEntry.TABLE_NAME);
        onCreate(db);

    }
}
