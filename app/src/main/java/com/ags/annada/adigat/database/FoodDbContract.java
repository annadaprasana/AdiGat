package com.ags.annada.adigat.database;

/**
 * Created by Annada on 13/10/2015.
 */
public class FoodDbContract {
    private static final String TAG = FoodDbContract.class.getSimpleName();

    public static final String PROVIDER_NAME = "com.ags.annada.adigat.database";
    public static final String TABLE_NAME_FOOD = "fooditem";
    public static final String CONTENT_URI = "content://" + PROVIDER_NAME + "/" + TABLE_NAME_FOOD;

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_FOOD_TITLE = "food_title";
    public static final String COLUMN_FOOD_PRICE = "food_price";
    public static final String COLUMN_FOOD_ICON = "food_icon";
    public static final String COLUMN_FOOD_DESC = "food_desc";
    public static final String COLUMN_NO_OF_ITEMS = "no_of_items";

    public static final String DATABASE_NAME = "food.db";

    public static final int DATABASE_VERSION = 1;

}
