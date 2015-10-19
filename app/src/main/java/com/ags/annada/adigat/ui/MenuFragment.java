package com.ags.annada.adigat.ui;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.content.CursorLoader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.ags.annada.adigat.R;
import com.ags.annada.adigat.adapters.FoodAdapter;
import com.ags.annada.adigat.database.FoodDbContract;

import java.lang.reflect.Field;

/**
 * Created by Annada on 23/09/2015.
 */
public class MenuFragment extends ListFragment
                            implements LoaderManager.LoaderCallbacks<Cursor>{
    private static final String TAG = MenuFragment.class.getSimpleName();

    private FoodAdapter mFoodAdapter;

    public MenuFragment(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mFoodAdapter = new FoodAdapter(getContext(),null,0);
        setListAdapter(mFoodAdapter);

        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // remove the dividers from the ListView of the ListFragment
        getListView().setDivider(null);
    }

    @Override
    public void onListItemClick(ListView l, View view, int position, long rowId) {
        updateRow(rowId);
    }

    private void updateRow(long rowId){
        ContentResolver cr = getContext().getContentResolver();
        String[] projection = new String[] { FoodDbContract.COLUMN_ID, FoodDbContract.COLUMN_NO_OF_ITEMS};
        int noOfItems = 0;

        Cursor cursor = cr.query(ContentUris.withAppendedId(Uri.parse(FoodDbContract.CONTENT_URI), rowId), projection, null, null, null);

        while(cursor.moveToNext()){
            noOfItems = cursor.getInt(cursor.getColumnIndexOrThrow(FoodDbContract.COLUMN_NO_OF_ITEMS));
        }

        noOfItems++;
        ContentValues contentValues = new ContentValues();
        contentValues.put(FoodDbContract.COLUMN_NO_OF_ITEMS, noOfItems);
        cr.update(ContentUris.withAppendedId(Uri.parse(FoodDbContract.CONTENT_URI), rowId), contentValues, null, null);
    }

    public void initLoader() {
        getLoaderManager().initLoader(0, null, this);
    }

    public void updateView(){
        mFoodAdapter.notifyDataSetChanged();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Uri uri = Uri.parse(FoodDbContract.CONTENT_URI);
        CursorLoader cursorLoader = new CursorLoader(getActivity(), uri, null, null, null, null);

        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if(!cursor.isClosed()){
            mFoodAdapter.swapCursor(cursor);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mFoodAdapter.swapCursor(null);
    }
}
