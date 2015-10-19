package com.ags.annada.adigat;

import android.graphics.drawable.Drawable;

/**
 * Created by Annada on 23/09/2015.
 */
public class FoodItem {
    private static final String TAG = FoodItem.class.getSimpleName();

    public int mNoOfPlates = 0;
    public String mFoodTitle = null;
    public double mPrice;
    public Drawable mIcon;

    public FoodItem(){
        super();
    }

    public FoodItem(int noOfPlates, String foodTitle, Drawable icon, double price){
        super();
        this.mNoOfPlates = noOfPlates;
        this.mFoodTitle = foodTitle;
        this.mPrice = price;
        this.mIcon = icon;
    }
}
