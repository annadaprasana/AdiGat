package com.ags.annada.adigat.ui;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.ScaleAnimation;

import com.ags.annada.adigat.adapters.OrderAdapter;
import com.ags.annada.adigat.R;
import com.ags.annada.adigat.database.FoodDbContract;

import jp.wasabeef.recyclerview.animators.OvershootInLeftAnimator;
import jp.wasabeef.recyclerview.animators.ScaleInAnimator;

/**
 * Created by Annada on 19/09/2015.
 */
public class OrderFragment extends Fragment
                            implements LoaderManager.LoaderCallbacks<Cursor>{

    private static final String TAG = OrderFragment.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private OrderAdapter mOrderAdapter;

    public OrderFragment(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.order_fragment,container,false);

        mRecyclerView = (RecyclerView)(rootView.findViewById(R.id.recycler_view));
        LinearLayoutManager llm = new LinearLayoutManager(getContext());

        OvershootInLeftAnimator animator = new OvershootInLeftAnimator(1.0f);
        animator.setAddDuration(600);
        animator.setRemoveDuration(600);
        animator.setMoveDuration(600);
        animator.setChangeDuration(600);
        mRecyclerView.setItemAnimator(animator);
        mRecyclerView.setLayoutManager(llm);

       return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mOrderAdapter = new OrderAdapter(getContext(),null);
        mRecyclerView.setAdapter(mOrderAdapter);
        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //getListView().setDivider(null);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "Inside onStart()");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.d(TAG, "Inside onHiddenChanged()" + Boolean.toString(hidden));
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "Inside onResume()");
    }

    public void updateView(){
        //mOrderItemList.clear();
        //mOrderAdapter.notifyDataSetChanged();
    }

    public void initLoader() {
        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = new String[]{FoodDbContract.COLUMN_ID,FoodDbContract.COLUMN_FOOD_TITLE,FoodDbContract.COLUMN_NO_OF_ITEMS,FoodDbContract.COLUMN_FOOD_ICON};
        String selection = new String(FoodDbContract.COLUMN_NO_OF_ITEMS + ">?");
        String[] selectionArgs = new String[]{"0"};
        CursorLoader cursorLoader = new CursorLoader(getContext(), Uri.parse(FoodDbContract.CONTENT_URI),projection,selection,selectionArgs,null);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        mOrderAdapter.swapCursor(cursor);

        //mOrderAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mOrderAdapter.swapCursor(null);
    }
}
