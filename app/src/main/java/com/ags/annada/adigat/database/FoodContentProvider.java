package com.ags.annada.adigat.database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.util.HashMap;

/**
 * Created by Annada on 13/10/2015.
 */
public class FoodContentProvider extends ContentProvider{
    private static final String TAG = FoodContentProvider.class.getSimpleName();

    private SQLiteDatabase db;

    private static HashMap<String, String> FOOD_PROJECTION_MAP;

    private static final int FOOD = 1;
    private static final int FOOD_ID = 2;

    static final UriMatcher uriMatcher;
    static{
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(FoodDbContract.PROVIDER_NAME, FoodDbContract.TABLE_NAME_FOOD, FOOD);
        uriMatcher.addURI(FoodDbContract.PROVIDER_NAME, FoodDbContract.TABLE_NAME_FOOD + "/#", FOOD_ID);
    }

    @Override
    public boolean onCreate() {
        boolean lReturn = false;

        Context context = getContext();
        FoodItemOpenHelper dbHelper = new FoodItemOpenHelper(context,FoodDbContract.DATABASE_NAME, null, FoodDbContract.DATABASE_VERSION);
        db = dbHelper.getWritableDatabase();

        if(db == null){
            lReturn = false;
        }else{
            lReturn = true;
        }

        return lReturn;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
            SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
            qb.setTables(FoodDbContract.TABLE_NAME_FOOD);

            switch (uriMatcher.match(uri)) {
                case FOOD:
                    qb.setProjectionMap(FOOD_PROJECTION_MAP);
                    break;
                case FOOD_ID:
                    qb.appendWhere( FoodDbContract.COLUMN_ID + "=" + uri.getPathSegments().get(1));
                    break;
                default:
                    throw new IllegalArgumentException("Unknown URI " + uri);
            }

            if (sortOrder == null || sortOrder == ""){
                /**
                 * By default sort on food names
                 */
                //sortOrder = FoodDbContract.COLUMN_FOOD_TITLE;
            }

        final Cursor cursor = qb.query(db, projection, selection, selectionArgs,
                    null, null, sortOrder);

            /**
             * register to watch a content URI for changes
             */
            cursor.setNotificationUri(getContext().getContentResolver(), uri);


        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)){
            /**
             * Get all food records
             */
            case FOOD:
                return "vnd.android.cursor.dir/vnd.example.foods";
            /**
             * Get a particular food
             */
            case FOOD_ID:
                return "vnd.android.cursor.item/vnd.example.foods";
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Uri _uri = null;
        long rowID = 0;

        if(uri.toString().equals(FoodDbContract.CONTENT_URI)){
            /**
             * Add a new student record
             */
            rowID = db.insert( FoodDbContract.TABLE_NAME_FOOD, "", values);
            /**
             * If record is added successfully
             */
            if (rowID > 0)
            {
                _uri = ContentUris.withAppendedId(Uri.parse(FoodDbContract.CONTENT_URI), rowID);
                getContext().getContentResolver().notifyChange(_uri, null);
                return _uri;
            }

            throw new SQLException("Failed to add a record into " + uri);
        }

        return _uri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = 0;

        //if(uri.toString().equals(FoodDbContract.CONTENT_URI)){
            switch (uriMatcher.match(uri)){
                case FOOD:
                    count = db.delete(FoodDbContract.TABLE_NAME_FOOD, selection, selectionArgs);
                    break;
                case FOOD_ID:
                    String id = uri.getPathSegments().get(1);
                    count = db.delete( FoodDbContract.TABLE_NAME_FOOD, FoodDbContract.COLUMN_ID + " = " + id +
                            (!TextUtils.isEmpty(selection) ? " AND (" +
                                    selection + ')' : ""), selectionArgs);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown URI " + uri);
            }
        //}

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int count = 0;

        switch (uriMatcher.match(uri)){
            case FOOD:
                count = db.update(FoodDbContract.TABLE_NAME_FOOD, values,
                        selection, selectionArgs);
                break;
            case FOOD_ID:
                count = db.update(FoodDbContract.TABLE_NAME_FOOD, values, FoodDbContract.COLUMN_ID +
                        " = " + uri.getPathSegments().get(1) +
                        (!TextUtils.isEmpty(selection) ? " AND (" +
                                selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri );
        }
        getContext().getContentResolver().notifyChange(uri, null);

        return count;
    }
}
