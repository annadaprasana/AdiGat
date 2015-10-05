package com.ags.annada.adigat.database;

/**
 * Created by Annada on 26/09/2015.
 */
public class Food {
    private long mId;
    private String mFoodTitle;
    private int mFoodPrice;

    public long getId(){
        return this.mId;
    }

    public void setId(long id){
        this.mId = id;
    }

    public String getFoodTitle(){
        return this.mFoodTitle;
    }

    public void setFoodTitle(String foodTitle){
        this.mFoodTitle = foodTitle;
    }

    public int getFoodPrice(){
        return mFoodPrice;
    }

    public void setFoodPrice(int foodPrice){
        this.mFoodPrice = foodPrice;
    }
}
