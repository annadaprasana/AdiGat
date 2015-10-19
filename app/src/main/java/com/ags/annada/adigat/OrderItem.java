package com.ags.annada.adigat;

import android.graphics.drawable.Drawable;

/**
 * Created by Annada on 24/09/2015.
 */
public class OrderItem {
    private static final String TAG = OrderItem.class.getSimpleName();

    public int mNoOfPlates;
    public String mFoodTitle;
    public String mFoodIcon;

    public OrderItem(){
    }

    public int getNoOfPlates(){
        return this.mNoOfPlates;
    }

    public void setNoOfPlates(int noOfPlates){
        this.mNoOfPlates = noOfPlates;
    }

    public String getFoodTitle(){
        return this.mFoodTitle;
    }

    public void setFoodTitle(String foodTitle){
        this.mFoodTitle = foodTitle;
    }

    public String getFoodIcon(){
        return this.mFoodIcon;
    }

    public void setFoodIcon(String foodIcon){
        this.mFoodIcon = foodIcon;
    }

    public OrderItem(String foodTitle, int noOfPlates, String foodIcon){
        this.mFoodTitle = foodTitle;
        this.mNoOfPlates = noOfPlates;
        this.mFoodIcon = foodIcon;
    }
}
