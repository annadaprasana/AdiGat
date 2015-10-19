package com.ags.annada.adigat.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Annada on 27/09/2015.
 */

//TODO this class needs to remove, as contentprovider is implemented
public class FoodItemDataSource {
    private static final String TAG = "FoodItemDataSource";

    private Context mContext;
    private SQLiteDatabase mDatabase;
    private FoodItemOpenHelper mDbHelper;
    private String[] allColumns = {FoodDbContract.COLUMN_ID,FoodDbContract.COLUMN_FOOD_TITLE,FoodDbContract.COLUMN_FOOD_PRICE,
            FoodDbContract.COLUMN_FOOD_ICON, FoodDbContract.COLUMN_FOOD_DESC, FoodDbContract.COLUMN_NO_OF_ITEMS};

    public FoodItemDataSource(Context context){
        mContext = context;
        mDbHelper = FoodItemOpenHelper.getInstance(context,FoodDbContract.DATABASE_NAME, null, FoodDbContract.DATABASE_VERSION);
    }

    public void open() throws SQLException{
        mDatabase = mDbHelper.getWritableDatabase();
    }

    public void close(){
        mDbHelper.close();
    }

    public void insert(String foodTitle, double foodPrice, String foodIcon, String foodDesc, int noOfItems){
        ContentValues contentValues = new ContentValues();
        contentValues.put(FoodDbContract.COLUMN_FOOD_TITLE, foodTitle);
        contentValues.put(FoodDbContract.COLUMN_FOOD_PRICE,foodPrice);
        contentValues.put(FoodDbContract.COLUMN_FOOD_ICON, foodIcon);
        contentValues.put(FoodDbContract.COLUMN_FOOD_DESC, foodDesc);
        contentValues.put(FoodDbContract.COLUMN_NO_OF_ITEMS, noOfItems);

        long insertID = mDatabase.insert(FoodDbContract.TABLE_NAME_FOOD,null,contentValues);
    }

    public void query(long id){
        Cursor cursor = mDatabase.query(FoodDbContract.TABLE_NAME_FOOD, allColumns, FoodDbContract.COLUMN_ID + " = " + id, null, null, null, null);
    }

    public Cursor query(){
        Cursor cursor = mDatabase.query(FoodDbContract.TABLE_NAME_FOOD, allColumns, null, null, null, null, null);
        return cursor;
    }

    public Cursor query(String selection){
        Cursor cursor = mDatabase.query(FoodDbContract.TABLE_NAME_FOOD, allColumns, selection, null, null, null, null);
        return cursor;
    }

    public int update(final ContentValues values, final String where, final String[] whereArgs){
        int rows = mDatabase.update(FoodDbContract.TABLE_NAME_FOOD, values, where, whereArgs);
        return rows;
    }

    public int getNoOfItems(Cursor cursor){
        return cursor.getInt(cursor.getColumnIndexOrThrow(FoodDbContract.COLUMN_NO_OF_ITEMS));
    }

    public double getFoodPrice(Cursor cursor){
        return cursor.getDouble(cursor.getColumnIndexOrThrow(FoodDbContract.COLUMN_FOOD_PRICE));
    }

    public String getFoodTitle(Cursor cursor){
        return cursor.getString(cursor.getColumnIndexOrThrow(FoodDbContract.COLUMN_FOOD_TITLE));
    }

    public String getFoodIcon(Cursor cursor){
        return cursor.getString(cursor.getColumnIndexOrThrow(FoodDbContract.COLUMN_FOOD_ICON));
    }

    public String getFoodDesc(Cursor cursor){
        return cursor.getString(cursor.getColumnIndexOrThrow(FoodDbContract.COLUMN_FOOD_DESC));
    }


    public int updateNoOfItems(final int newValue, final long id){
        ContentValues values = new ContentValues();
        values.put(FoodDbContract.COLUMN_NO_OF_ITEMS,newValue);

        String whereClause = FoodDbContract.COLUMN_ID + "=" + id;

        int rows = mDatabase.update(FoodDbContract.TABLE_NAME_FOOD, values, whereClause, null);

        return rows;
    }

    public void delete(long id){
        mDatabase.delete(FoodDbContract.TABLE_NAME_FOOD,FoodDbContract.COLUMN_ID + " = " + id, null);
    }

    public List<Food> getAllFoodItems(){
        List<Food> foodItems = new ArrayList<Food>();

        Cursor cursor = mDatabase.query(FoodDbContract.TABLE_NAME_FOOD, allColumns, null, null, null, null, null);

        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            Food foodItem = new Food();

            foodItem.setId(cursor.getLong(cursor.getColumnIndexOrThrow(FoodDbContract.COLUMN_ID)));
            foodItem.setFoodTitle(cursor.getString(cursor.getColumnIndexOrThrow(FoodDbContract.COLUMN_FOOD_TITLE)));
            foodItem.setFoodPrice(cursor.getInt(cursor.getColumnIndexOrThrow(FoodDbContract.COLUMN_FOOD_PRICE)));
            foodItem.setFoodIcon(cursor.getString(cursor.getColumnIndexOrThrow(FoodDbContract.COLUMN_FOOD_ICON)));
            foodItem.setFoodDesc(cursor.getString(cursor.getColumnIndexOrThrow(FoodDbContract.COLUMN_FOOD_DESC)));
            foodItem.setNoOfItems(cursor.getInt(cursor.getColumnIndexOrThrow(FoodDbContract.COLUMN_NO_OF_ITEMS)));

            foodItems.add(foodItem);

            cursor.moveToNext();
        }

        cursor.close();

        return foodItems;
    }
}
