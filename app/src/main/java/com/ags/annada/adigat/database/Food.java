package com.ags.annada.adigat.database;

/**
 * Created by Annada on 26/09/2015.
 */
public class Food {
    private long mId;
    private int mNoOfItems;
    private int mFoodPrice;
    private String mFoodTitle;
    private String mFoodIcon;
    private String mFoodDesc;


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

    public String getFoodIcon(){
        return this.mFoodIcon;
    }

    public void setFoodIcon(String foodIcon){
        this.mFoodIcon = foodIcon;
    }

    public int getNoOfItems(){
        return mNoOfItems;
    }

    public void setNoOfItems(int noOfItems){
        this.mNoOfItems = mNoOfItems;
    }

    public String getFoodDesc(){
        return this.mFoodDesc;
    }

    public void setFoodDesc(String foodDesc){
        this.mFoodDesc = foodDesc;
    }


}
