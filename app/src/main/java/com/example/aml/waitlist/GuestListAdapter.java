package com.example.aml.waitlist;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aml.waitlist.data.WaitlistContract;

/**
 * Created by aml on 10/04/18.
 */

public class GuestListAdapter extends RecyclerView.Adapter<GuestListAdapter.GuestViewHolder> {

    private Context context ;
 private Cursor mCursor ;

    public GuestListAdapter(Context context, Cursor mCursor) {
        this.context = context;
        this.mCursor = mCursor;
    }


    @Override
    public GuestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        // Get the RecyclerView item layout
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.guest_list_item , parent  , false);
        return  new GuestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GuestViewHolder holder, int position) {

        if(!mCursor.moveToPosition(position)) {
            return;
        }
        String name = mCursor.getString(mCursor.getColumnIndex(WaitlistContract.WaitlistEntry.COLUMN_GUEST_NAME));
        int party_size = mCursor.getInt(mCursor.getColumnIndex(WaitlistContract.WaitlistEntry.COLUMN_PARTY_SIZE));
        holder.name_text_view.setText(name);
        holder.party_size_text_view.setText(String.valueOf(party_size));

        Long id = mCursor.getLong(mCursor.getColumnIndex(WaitlistContract.WaitlistEntry._ID));
        holder.itemView.setTag(id);

    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public void swapCursor (Cursor newCursor) {
        if (mCursor != null)  mCursor.close();
        mCursor = newCursor ;
        if (newCursor != null){
            this.notifyDataSetChanged();
        }
    }

    class GuestViewHolder extends RecyclerView.ViewHolder{
         TextView party_size_text_view ;
        TextView name_text_view ;
        public GuestViewHolder(View itemView) {
            super(itemView);
            party_size_text_view = (TextView) itemView.findViewById(R.id.party_size_text_view);
            name_text_view = (TextView) itemView.findViewById(R.id.name_text_view);
        }
    }



}
