package com.ags.annada.adigat;

import android.app.Application;

import com.ags.annada.adigat.database.FoodItemDataSource;

/**
 * Created by Annada on 27/09/2015.
 */
public class AdigetApp extends Application{

    //private FoodItemOpenHelper mFoodItemOpenHelper;
    private FoodItemDataSource mFoodItemDataSource;
    String[] mFoodItems = {"Chicken","Lamb","Fish","Rice","Dal"};
    double[] mPrice = {5.30,6.30,7.25,8.65,2.90};

    @Override
    public void onCreate() {
        super.onCreate();

        //mFoodItemOpenHelper = new FoodItemOpenHelper(getApplicationContext());
        mFoodItemDataSource = new FoodItemDataSource(getApplicationContext());
        mFoodItemDataSource.open();

        mFoodItemDataSource.insert(mFoodItems[0],mPrice[0]);
        mFoodItemDataSource.insert(mFoodItems[1],mPrice[1]);
        mFoodItemDataSource.insert(mFoodItems[2],mPrice[2]);
        mFoodItemDataSource.insert(mFoodItems[3],mPrice[3]);
        mFoodItemDataSource.insert(mFoodItems[4],mPrice[4]);
    }

    public FoodItemDataSource getFoodItemDataSource(){
        return mFoodItemDataSource;
    }
}
