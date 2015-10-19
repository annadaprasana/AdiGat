package com.ags.annada.adigat.services;

import android.app.IntentService;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;

import com.ags.annada.adigat.database.FoodDbContract;
import com.ags.annada.adigat.database.FoodItemDataSource;
import com.ags.annada.adigat.database.FoodItemOpenHelper;
import com.ags.annada.adigat.events.DBOperationFinished;
import com.ags.annada.adigat.json.FoodModel;
import com.ags.annada.adigat.settings.Settings;

import java.util.ArrayList;

import de.greenrobot.event.EventBus;

/**
 * Created by Annada on 10/10/2015.
 */
public class StoreInDBService extends IntentService{
    private static final String TAG = StoreInDBService.class.getSimpleName();

    public static final int DB_STATUS_FINISHED = 3;
    public static final int DB_STATUS_ERROR = 4;

    //private FoodItemDataSource mFoodItemDataSource;
    private Settings mSettings;

    public StoreInDBService() {
        super(StoreInDBService.class.getSimpleName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //final ResultReceiver receiver = intent.getParcelableExtra("receiver");
        //mFoodItemDataSource = new FoodItemDataSource(getApplicationContext());
        //mFoodItemDataSource.open();
        ContentResolver contentResolver = getApplicationContext().getContentResolver();
        deleteAllRows(getApplicationContext());

        //ArrayList<FoodModel> foodModels = intent.getParcelableExtra("foods");
        ArrayList<FoodModel> foodModels = (ArrayList<FoodModel>)intent.getSerializableExtra("foods");

        for(FoodModel food : foodModels){
            //mFoodItemDataSource.insert(food.getTitle(),food.getPrice(),food.getImage(),food.getDesc(),0);
            ContentValues value = new ContentValues();
            value.put(FoodDbContract.COLUMN_FOOD_TITLE,food.getTitle());
            value.put(FoodDbContract.COLUMN_FOOD_PRICE,food.getPrice());
            value.put(FoodDbContract.COLUMN_FOOD_ICON,food.getImage());
            value.put(FoodDbContract.COLUMN_FOOD_DESC,food.getDesc());
            value.put(FoodDbContract.COLUMN_NO_OF_ITEMS,0);

            Uri insertedRowId = contentResolver.insert(Uri.parse(FoodDbContract.CONTENT_URI), value);
            Log.i(TAG, "ID of inserted row: " + insertedRowId);
        }

        //receiver.send(DB_STATUS_FINISHED, null);

        DBOperationFinished dbStatus = new DBOperationFinished();
        dbStatus.setStatus("success");

        EventBus.getDefault().post(dbStatus);
    }

    /*empties the database*/
    public static void deleteAllRows(Context context) {
        ContentResolver contentResolver = context.getContentResolver();
        String selection = null;
        String[] selectionArguments = null;
        int numberRowsDeleted = contentResolver.delete(Uri.parse(FoodDbContract.CONTENT_URI), selection, selectionArguments);
        Log.i(TAG, "Number rows deleted: " + numberRowsDeleted);
    }
}
