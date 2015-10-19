package com.ags.annada.adigat.adapters;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ags.annada.adigat.OrderItem;
import com.ags.annada.adigat.R;
import com.ags.annada.adigat.database.FoodDbContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Annada on 24/09/2015.
 */
public class OrderAdapter extends CursorRecyclerViewAdapter<OrderAdapter.OrderViewHolder>{
    private static final String TAG = OrderAdapter.class.getSimpleName();

    private Context mContext;

    public OrderAdapter(Context context, Cursor cursor){
        super(context, cursor);
        mContext = context;
    }

    @Override
    public OrderViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.order_item_row, viewGroup, false);
        OrderViewHolder orderViewHolder = new OrderViewHolder(v, new OrderViewHolder.updateListener(){
            public void update(int position) {updateItem(position); }
        });

        return orderViewHolder;
    }

    @Override
    public void onBindViewHolder(OrderViewHolder orderViewHolder, final Cursor cursor) {
        orderViewHolder.mTVfoodTitle.setText(cursor.getString(cursor.getColumnIndexOrThrow(FoodDbContract.COLUMN_FOOD_TITLE)));
        orderViewHolder.mTVnoOfPlates.setText(Integer.toString(cursor.getInt(cursor.getColumnIndexOrThrow(FoodDbContract.COLUMN_NO_OF_ITEMS))));

        //Food Image
        Resources resources = mContext.getResources();
        String foodIcon = cursor.getString(cursor.getColumnIndexOrThrow(FoodDbContract.COLUMN_FOOD_ICON));

        int imageID = resources.getIdentifier(foodIcon, "mipmap", mContext.getPackageName());

        if(imageID != 0) {
            orderViewHolder.mIVimgIcon.setImageDrawable(resources.getDrawable(imageID));
        }
        else{
            int noImageID = resources.getIdentifier("no_item","mipmap",mContext.getPackageName());
            orderViewHolder.mIVimgIcon.setImageDrawable(resources.getDrawable(noImageID));
        }

        //Remove Image
        int removeImageID = resources.getIdentifier("cross", "mipmap", mContext.getPackageName());
        if(removeImageID != 0) {
            orderViewHolder.mIVimgRemove.setImageDrawable(resources.getDrawable(removeImageID));
        }
        else{
            int noImageID = resources.getIdentifier("no_item","mipmap",mContext.getPackageName());
            orderViewHolder.mIVimgIcon.setImageDrawable(resources.getDrawable(noImageID));
        }
    }

    public void updateItem(int position) {
        long rowId = getItemId(position);
        ContentValues contentValues = new ContentValues();
        contentValues.put(FoodDbContract.COLUMN_NO_OF_ITEMS, 0);

        ContentResolver cr = mContext.getContentResolver();
        cr.update(ContentUris.withAppendedId(Uri.parse(FoodDbContract.CONTENT_URI), rowId),contentValues, null, null);

        notifyItemRemoved(position);
        Log.i(TAG, "row updated: " + rowId + " at position:" + position);

        //notifyItemRemoved(position);
        //notifyItemRangeChanged(position, getCursor().getCount());
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder{
        private TextView mTVfoodTitle;
        private TextView mTVnoOfPlates;
        private ImageView mIVimgIcon;
        private ImageView mIVimgRemove;
        public updateListener mListener;

        OrderViewHolder(View itemView, updateListener listener) {
            super(itemView);
            mTVfoodTitle = (TextView)itemView.findViewById(R.id.foodTitle);
            mTVnoOfPlates = (TextView)itemView.findViewById(R.id.noOfItems);
            mIVimgIcon = (ImageView)itemView.findViewById(R.id.food_image);
            mIVimgRemove = (ImageView)itemView.findViewById(R.id.remove_image);

            mIVimgRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.update(getAdapterPosition());
                }
            });

            mListener = listener;
        }

        public static interface updateListener {
            public void update(int position);
        }
    }
}