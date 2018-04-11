package com.example.aml.waitlist;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.aml.waitlist.data.WaitlistContract;
import com.example.aml.waitlist.data.WaitlistDbHelper;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase db;
    private GuestListAdapter guestListAdapter;
    RecyclerView guest_list_recycler_view;
    EditText person_name_edit_text;
    EditText party_count_edit_text;

    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        guest_list_recycler_view = (RecyclerView) findViewById(R.id.guest_list_recycler_view);
        person_name_edit_text = (EditText) findViewById(R.id.person_name_edit_text);
        party_count_edit_text = (EditText) findViewById(R.id.party_count_edit_text);

        guest_list_recycler_view.setLayoutManager(new LinearLayoutManager(this));

        WaitlistDbHelper waitlistDbHelper = new WaitlistDbHelper(this);
        db = waitlistDbHelper.getWritableDatabase();
     //  TestUtil.insertFakeData(db);

        Cursor cursor = getAllGuests();
        Log.e("LIST SIZE", cursor.getCount() + "List Size");
        guestListAdapter = new GuestListAdapter(this, cursor);
        guest_list_recycler_view.setAdapter(guestListAdapter);


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0 ,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

                Long id = (Long) viewHolder.itemView.getTag();
                removeGuest(id);
                guestListAdapter.swapCursor(getAllGuests());
            }
        }).attachToRecyclerView(guest_list_recycler_view);
    }

    private Cursor getAllGuests() {
        return db.query(
                WaitlistContract.WaitlistEntry.TABLE_NAME,
                null, null, null, null, null, WaitlistContract.WaitlistEntry.COLUMN_TIMESTAMP
        );
    }

    private long addNewGuests(String name, int partySize) {
        ContentValues cv = new ContentValues();
        cv.put(WaitlistContract.WaitlistEntry.COLUMN_GUEST_NAME, name);
        cv.put(WaitlistContract.WaitlistEntry.COLUMN_PARTY_SIZE, partySize);
        return db.insert(WaitlistContract.WaitlistEntry.TABLE_NAME, null, cv);
    }


    public void addToWaitlist(View view) {

        if (person_name_edit_text.getText().length() == 0 || party_count_edit_text.getText().length() == 0) {
            return;
        }

        int paartySize = 1;
        try {
            paartySize = Integer.parseInt(party_count_edit_text.getText().toString());
        } catch (NumberFormatException ex) {
            Log.e(LOG_TAG, "Failed To Party Size text to number : " + ex.getMessage());

        }

    Long a =     addNewGuests(person_name_edit_text.getText().toString(), paartySize);
        Log.e(LOG_TAG , a+"insert");
       guestListAdapter.swapCursor(getAllGuests());

        party_count_edit_text.clearFocus();
        party_count_edit_text.getText().clear();
        person_name_edit_text.getText().clear();
    }

    private boolean  removeGuest (Long id) {
        return db.delete(WaitlistContract.WaitlistEntry.TABLE_NAME , WaitlistContract.WaitlistEntry._ID
        +"="+id , null) > 0 ;
    }


}
