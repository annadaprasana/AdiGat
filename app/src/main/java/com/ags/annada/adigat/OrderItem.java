package com.ags.annada.adigat;

import android.graphics.drawable.Drawable;

/**
 * Created by Annada on 24/09/2015.
 */
public class OrderItem {
    private static final String TAG = "OrderItem";

    String mFoodTitle;
    int mNoOfPlates;
    Drawable mIcon;

    public OrderItem(String foodTitle, int noOfPlates, Drawable icon){
        this.mFoodTitle = foodTitle;
        this.mNoOfPlates = noOfPlates;
        this.mIcon = icon;
    }
}
