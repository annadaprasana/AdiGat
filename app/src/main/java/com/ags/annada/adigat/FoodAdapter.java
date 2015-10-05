package com.ags.annada.adigat;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

/**
 * Created by Annada on 26/09/2015.
 */

public class FoodAdapter extends SimpleCursorAdapter{

    /**
     * Standard constructor.
     *
     * @param context The context where the ListView associated with this
     *                SimpleListItemFactory is running
     * @param layout  resource identifier of a layout file that defines the views
     *                for this list item. The layout file should include at least
     *                those named views defined in "to"
     * @param c       The database cursor.  Can be null if the cursor is not available yet.
     * @param from    A list of column names representing the data to bind to the UI.  Can be null
     *                if the cursor is not available yet.
     * @param to      The views that should display column in the "from" parameter.
     *                These should all be TextViews. The first N views in this list
     *                are given the values of the first N columns in the from
     *                parameter.  Can be null if the cursor is not available yet.
     * @param flags   Flags used to determine the behavior of the adapter,
     *                as per {@link CursorAdapter#CursorAdapter(Context, Cursor, int)}.
     */
    public FoodAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
    }
}



/*
public class FoodAdapter extends CursorAdapter{

    public FoodAdapter(Context context, Cursor cursor, int flags) {
        super(context, cursor, false);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.food_item_row,parent,false);
    }

    //This method binds the data to the given view.
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        FoodItemHolder foodItemHolder = new FoodItemHolder();

        foodItemHolder.tvNoOfPlates = (TextView)view.findViewById(R.id.noOfItems);
        foodItemHolder.tvFoodTitle = (TextView)view.findViewById(R.id.itemName);
        foodItemHolder.ivFoodIcon = (ImageView)view.findViewById(R.id.foodtemIcon);
        foodItemHolder.tvFoodPrice = (TextView)view.findViewById(R.id.price);

        foodItemHolder.tvFoodPrice.setText(cursor.getColumnIndexOrThrow("noOfItems"));
        foodItemHolder.tvFoodTitle.setText(cursor.getColumnIndexOrThrow("itemTitle"));
        //foodItemHolder.ivFoodIcon.setImageDrawable(cursor.getColumnIndexOrThrow("foodItemIcon"));
        foodItemHolder.tvFoodPrice.setText(cursor.getColumnIndexOrThrow("foodPrice"));
    }

    static class FoodItemHolder{
        TextView tvNoOfPlates;
        TextView tvFoodTitle;
        ImageView ivFoodIcon;
        TextView tvFoodPrice;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }
}
*/