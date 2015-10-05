package com.ags.annada.adigat;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Annada on 19/09/2015.
 */
public class OrderFragment extends ListFragment {
    private static final String TAG = "OrderFragment";

    //int color;
    private List<OrderItem> mOrderItemList; //Order Item List
    OrderAdapter mOrderAdapter;

    public OrderFragment(){
    }

    /*
    public OrderFragment(int color) {
        this.color = color;
    }


    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        this.color = args.getInt("color");
    }
    */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        mOrderItemList = new ArrayList<OrderItem>();

        //TODO: Since mFoodItemsList is used in MenuFragment & OrderFragment classes, make FoodItemsList to a separate singleton class.

        for(int i =0; i < MenuFragment.mFoodItemsList.size(); i++){
            //Which item are ordered, show those only
            if(MenuFragment.mFoodItemsList.get(i).mNoOfPlates > 0) {
                mOrderItemList.add(new OrderItem(MenuFragment.mFoodItemsList.get(i).mFoodTitle, MenuFragment.mFoodItemsList.get(i).mNoOfPlates, MenuFragment.mFoodItemsList.get(i).mIcon));
            }
        }

        mOrderAdapter = new OrderAdapter(getActivity(),mOrderItemList);
        setListAdapter(mOrderAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.order_fragment,container,false);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //getListView().setEmptyView(getView().findViewById(R.id.emptyElement));

        //ListView listView = (ListView) getView().findViewById(android.R.id.list);
        //listView.setEmptyView(getView().findViewById(android.R.id.empty));

        getListView().setDivider(null);
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
        //mOrderItemList = new ArrayList<OrderItem>();

        mOrderItemList.clear();

        for(int i =0; i < MenuFragment.mFoodItemsList.size(); i++){
            //Which item are ordered, show those only
            if(MenuFragment.mFoodItemsList.get(i).mNoOfPlates > 0) {
                mOrderItemList.add(new OrderItem(MenuFragment.mFoodItemsList.get(i).mFoodTitle, MenuFragment.mFoodItemsList.get(i).mNoOfPlates, MenuFragment.mFoodItemsList.get(i).mIcon));
            }
        }

        mOrderAdapter.notifyDataSetChanged();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        if(mOrderItemList.get(position).mNoOfPlates > 0){
            mOrderItemList.get(position).mNoOfPlates--;

            int menuIndex = 0;

            for(FoodItem foodItem :  MenuFragment.mFoodItemsList) {
                if (mOrderItemList.get(position).mFoodTitle == foodItem.mFoodTitle) {
                    MenuFragment.mFoodItemsList.get(menuIndex).mNoOfPlates--;
                }
                menuIndex++;
            }
        }

        mOrderAdapter.notifyDataSetChanged();
    }
}
