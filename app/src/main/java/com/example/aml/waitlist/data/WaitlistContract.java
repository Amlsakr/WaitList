package com.example.aml.waitlist.data;

import android.provider.BaseColumns;

/**
 * Created by aml on 10/04/18.
 */

public class WaitlistContract {

    public  static  final class WaitlistEntry implements BaseColumns {
private WaitlistEntry (){

}



        // COLUMN_TIMESTAMP -> timestamp
        public static final String   TABLE_NAME = "waitlist";
        public static final String  COLUMN_GUEST_NAME = "guestName";
        public static final String COLUMN_PARTY_SIZE = "partySize";

        public static final String  COLUMN_TIMESTAMP = "timestamp";

    }
}
