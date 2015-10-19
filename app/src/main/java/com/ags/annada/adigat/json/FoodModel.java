package com.ags.annada.adigat.json;

import java.io.Serializable;

/**
 * Created by Annada on 09/10/2015.
 */
public class FoodModel implements Serializable{
    private static final String TAG = FoodModel.class.getSimpleName();

    private String mTitle;
    private double mPrice;
    private String mImage;
    private String mDesc;

    public String getTitle(){
        return this.mTitle;
    }

    public void setTitle(String title){
        this.mTitle = title;
    }

    public double getPrice(){
        return this.mPrice;
    }

    public void setPrice(double price){
        this.mPrice = price;
    }

    public String getImage(){
        return this.mImage;
    }

    public void setImage(String image){
        this.mImage = image;
    }

    public String getDesc(){
        return this.mDesc;
    }

    public void setDesc(String desc){
        this.mDesc = desc;
    }

}
