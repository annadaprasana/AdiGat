package com.ags.annada.adigat;

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
    private static final String TAG = "FoodItemOpenHelper";

    private static FoodItemOpenHelper mInstance = null;
    private Context mContext;

    public static final String TABLE_NAME_FOOD = "fooditem";

    public static final String COLUMN_ID = "_id";

    public static final String COLUMN_FOOD_TITLE = "food_title";
    public static final String COLUMN_FOOD_PRICE = "food_price";
    public static final String COLUMN_FOOD_ICON = "food_icon";

    public static final String DATABASE_NAME = "food.db";

    public static final int DATABASE_VERSION = 1;

    //create table fooditem (_id integer primary key autoincrement, food_title text not null food_price integer);


    public static final String DATABASE_CREATE = "create table " + TABLE_NAME_FOOD + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_FOOD_TITLE + " text not null, " + COLUMN_FOOD_PRICE + " integer);";


    /*
    public FoodItemOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public FoodItemOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    */

    //Private Constructor
    private FoodItemOpenHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = context;
    }

    public static FoodItemOpenHelper getInstance(Context context){
        if(mInstance == null){
            mInstance = new FoodItemOpenHelper(context.getApplicationContext());
        }

        return mInstance;
    }

    /**
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     *
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    /**
     * Called when the database needs to be upgraded. The implementation
     * should use this method to drop tables, add tables, or do anything else it
     * needs to upgrade to the new schema version.
     * <p/>
     * <p>
     * The SQLite ALTER TABLE documentation can be found
     * <a href="http://sqlite.org/lang_altertable.html">here</a>. If you add new columns
     * you can use ALTER TABLE to insert them into a live table. If you rename or remove columns
     * you can use ALTER TABLE to rename the old table, then create the new table and then
     * populate the new table with the contents of the old table.
     * </p><p>
     * This method executes within a transaction.  If an exception is thrown, all changes
     * will automatically be rolled back.
     * </p>
     *
     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG,"Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_FOOD);

        onCreate(db);
    }
}
