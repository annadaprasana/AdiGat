package com.ags.annada.adigat.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Annada on 26/09/2015.
 */

//This class is a singleton class to ensure that there exist only one helper instance.
//But no need to make database helper as Singleton if it use with ContentProviders.
public class FoodItemOpenHelper extends SQLiteOpenHelper{
    private static final String TAG = FoodItemOpenHelper.class.getSimpleName();

    private static FoodItemOpenHelper mInstance = null;
    private Context mContext;


    //create table fooditem (_id integer primary key autoincrement, food_title text not null, food_price double, food_icon text not null, food_desc text, no_of_items integer);


    public static final String DATABASE_CREATE = "create table " + FoodDbContract.TABLE_NAME_FOOD + "("
            + FoodDbContract.COLUMN_ID + " integer primary key autoincrement, "
            + FoodDbContract.COLUMN_FOOD_TITLE + " text not null, " + FoodDbContract.COLUMN_FOOD_PRICE + " double, "
            + FoodDbContract.COLUMN_FOOD_ICON + " text not null, " + FoodDbContract.COLUMN_FOOD_DESC + " text, " + FoodDbContract.COLUMN_NO_OF_ITEMS + " integer);";


    /*
    public FoodItemOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public FoodItemOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    */

    //Private Constructor
    public FoodItemOpenHelper(Context context,String name,SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, null, version);
        this.mContext = context;
    }


    public static FoodItemOpenHelper getInstance(Context context,String name,SQLiteDatabase.CursorFactory factory, int version){
        if(mInstance == null){
            mInstance = new FoodItemOpenHelper(context,name,factory,version);
        }

        return mInstance;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG,"Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");

        db.execSQL("DROP TABLE IF EXISTS " + FoodDbContract.TABLE_NAME_FOOD);

        onCreate(db);
    }
}
