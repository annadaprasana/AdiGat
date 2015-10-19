package com.ags.annada.adigat;

/**
 * Created by Annada on 19/09/2015.
 */
public class FoodItem1 {
    private static final String TAG = FoodItem1.class.getSimpleName();

    public int mNoOfPlates = 0;
    public String mFoodTitle = null;
    public double mPrice;
    public int mIcon;
    public int noOfPlates = 0;

    public FoodItem1(){
        super();
    }

    public FoodItem1(int noOfPlates, String foodTitle, int icon, double price){
        super();
        this.mNoOfPlates = noOfPlates;
        this.mFoodTitle = foodTitle;
        this.mPrice = price;
        this.mIcon = icon;
    }
}
