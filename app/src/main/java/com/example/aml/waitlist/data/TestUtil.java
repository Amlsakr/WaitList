package com.example.aml.waitlist.data;

import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aml on 10/04/18.
 */

public class TestUtil {


    public static  void  insertFakeData(SQLiteDatabase db) {
        if (db == null){
            return;
        }
        // Create a list of fake guests
        List<ContentValues> list = new ArrayList<ContentValues>();
        ContentValues cv = new ContentValues();
        cv.put(WaitlistContract.WaitlistEntry.COLUMN_GUEST_NAME , "John");
        cv.put(WaitlistContract.WaitlistEntry.COLUMN_PARTY_SIZE , 12);
        list.add(cv);

        cv = new ContentValues();
        cv.put(WaitlistContract.WaitlistEntry.COLUMN_GUEST_NAME , "Larry");
        cv.put(WaitlistContract.WaitlistEntry.COLUMN_PARTY_SIZE ,1);
        list.add(cv);

        cv = new ContentValues();
        cv.put(WaitlistContract.WaitlistEntry.COLUMN_GUEST_NAME , "JESSICA");
        cv.put(WaitlistContract.WaitlistEntry.COLUMN_PARTY_SIZE ,99);
        list.add(cv);

        cv = new ContentValues();
        cv.put(WaitlistContract.WaitlistEntry.COLUMN_GUEST_NAME , "KIM");
        cv.put(WaitlistContract.WaitlistEntry.COLUMN_PARTY_SIZE ,45);
        list.add(cv);



        //insert all guests in one transaction
        try {
            db.beginTransaction();
            //clear the table first
            db.delete(WaitlistContract.WaitlistEntry.TABLE_NAME , null , null);
            //go through the list and add one by one
            for (ContentValues c : list){
                db.insert(WaitlistContract.WaitlistEntry.TABLE_NAME ,null , c);

            }
            db.setTransactionSuccessful();
        } catch (SQLException e) {

        }
        finally {
            db.endTransaction();
        }
    }


}
