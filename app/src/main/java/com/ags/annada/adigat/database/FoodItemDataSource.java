package com.ags.annada.adigat.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.ags.annada.adigat.FoodItemOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Annada on 27/09/2015.
 */
public class FoodItemDataSource {
    private static final String TAG = "FoodItemDataSource";

    private SQLiteDatabase mDatabase;
    private FoodItemOpenHelper mDbHelper;
    private String[] allColumns = {FoodItemOpenHelper.COLUMN_ID,FoodItemOpenHelper.COLUMN_FOOD_TITLE,FoodItemOpenHelper.COLUMN_FOOD_PRICE};

    public FoodItemDataSource(Context context){
        mDbHelper = FoodItemOpenHelper.getInstance(context);
    }

    public void open() throws SQLException{
        mDatabase = mDbHelper.getWritableDatabase();
    }

    public void close(){
        mDbHelper.close();
    }

    public void insert(String foodTitle, double foodPrice){
        ContentValues contentValues = new ContentValues();
        contentValues.put(FoodItemOpenHelper.COLUMN_FOOD_TITLE, foodTitle);
        contentValues.put(FoodItemOpenHelper.COLUMN_FOOD_PRICE,foodPrice*100);

        long insertID = mDatabase.insert(FoodItemOpenHelper.TABLE_NAME_FOOD,null,contentValues);
    }

    public void query(long id){
        Cursor cursor = mDatabase.query(FoodItemOpenHelper.TABLE_NAME_FOOD, allColumns, FoodItemOpenHelper.COLUMN_ID + " = " + id, null, null, null, null);
    }

    public void delete(long id){
        mDatabase.delete(FoodItemOpenHelper.TABLE_NAME_FOOD,FoodItemOpenHelper.COLUMN_ID + " = " + id, null);
    }

    public List<Food> getAllFoodItems(){
        List<Food> foodItems = new ArrayList<Food>();

        Cursor cursor = mDatabase.query(FoodItemOpenHelper.TABLE_NAME_FOOD, allColumns, null, null, null, null, null);

        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            Food foodItem = new Food();

            foodItem.setId(cursor.getLong(0));
            foodItem.setFoodTitle(cursor.getString(1));
            foodItem.setFoodPrice(cursor.getInt(2));

            foodItems.add(foodItem);

            cursor.moveToNext();
        }

        cursor.close();

        return foodItems;
    }
}
