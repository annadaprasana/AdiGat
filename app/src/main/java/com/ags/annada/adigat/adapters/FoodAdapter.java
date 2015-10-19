package com.ags.annada.adigat.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.ags.annada.adigat.R;
import com.ags.annada.adigat.database.FoodDbContract;
import com.ags.annada.adigat.database.FoodItemDataSource;

import java.text.DecimalFormat;

/**
 * Created by Annada on 26/09/2015.
 */

public class FoodAdapter extends CursorAdapter {

    public FoodAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.food_item_row,parent,false);
    }

    //This method binds the data to the given view.
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        FoodItemHolder foodItemHolder = new FoodItemHolder();

        foodItemHolder.mIVfoodIcon = (ImageView)view.findViewById(R.id.food_image);
        foodItemHolder.mTVnoOfPlates = (TextView)view.findViewById(R.id.noOfItems);
        foodItemHolder.mTVfoodTitle = (TextView)view.findViewById(R.id.foodTitle);
        foodItemHolder.mTVfoodPrice = (TextView)view.findViewById(R.id.foodPrice);
        foodItemHolder.mTVfoodDesc = (TextView)view.findViewById(R.id.food_desc);
        foodItemHolder.mIVselectIcon = (ImageView)view.findViewById(R.id.plusSignIcon);

        //Food Image
        //foodItemHolder.ivFoodIcon.setImageDrawable(cursor.getColumnIndexOrThrow("foodItemIcon"));
        Resources resources = context.getResources();
        String foodIcon = cursor.getString(cursor.getColumnIndexOrThrow(FoodDbContract.COLUMN_FOOD_ICON));
        int imageID = resources.getIdentifier(foodIcon, "mipmap",context.getPackageName());

        if(imageID != 0) {
            foodItemHolder.mIVfoodIcon.setImageDrawable(resources.getDrawable(imageID));
        }
        else{
            int noImageID = resources.getIdentifier("no_item","mipmap",context.getPackageName());
            foodItemHolder.mIVfoodIcon.setImageDrawable(resources.getDrawable(noImageID));
        }

        //Food Title
        String foodTitle = cursor.getString(cursor.getColumnIndexOrThrow(FoodDbContract.COLUMN_FOOD_TITLE));
        foodItemHolder.mTVfoodTitle.setText(foodTitle.substring(0, 1).toUpperCase() + foodTitle.substring(1));

        //No Of Items
        int noOfItems = cursor.getInt(cursor.getColumnIndexOrThrow(FoodDbContract.COLUMN_NO_OF_ITEMS));
        foodItemHolder.mTVnoOfPlates.setText(Integer.toString(noOfItems));

        //Food Price
        double price = cursor.getDouble(cursor.getColumnIndexOrThrow(FoodDbContract.COLUMN_FOOD_PRICE));
        DecimalFormat form = new DecimalFormat("0.00");
        foodItemHolder.mTVfoodPrice.setText("£" + form.format(price) );

        //Food Description
        String foodDesc = cursor.getString(cursor.getColumnIndexOrThrow("food_desc"));
        foodItemHolder.mTVfoodDesc.setText(foodDesc.substring(0,1).toUpperCase() + foodDesc.substring(1));

        if(noOfItems > 0) {
            //Select Image
            int selectImageID = resources.getIdentifier("selected", "mipmap", context.getPackageName());

            if (selectImageID != 0) {
                foodItemHolder.mIVselectIcon.setImageDrawable(resources.getDrawable(selectImageID));
            } else {
                int noImageID = resources.getIdentifier("no_item", "mipmap", context.getPackageName());
                foodItemHolder.mIVselectIcon.setImageDrawable(resources.getDrawable(noImageID));
            }
        }else {
            //Select Image
            int selectImageID = resources.getIdentifier("select", "mipmap", context.getPackageName());

            if (selectImageID != 0) {
                foodItemHolder.mIVselectIcon.setImageDrawable(resources.getDrawable(selectImageID));
            } else {
                int noImageID = resources.getIdentifier("no_item", "mipmap", context.getPackageName());
                foodItemHolder.mIVselectIcon.setImageDrawable(resources.getDrawable(noImageID));
            }
        }
    }

    static class FoodItemHolder{
        ImageView mIVfoodIcon;
        TextView mTVnoOfPlates;
        TextView mTVfoodTitle;
        TextView mTVfoodPrice;
        TextView mTVfoodDesc;
        ImageView mIVselectIcon;
    }
}
