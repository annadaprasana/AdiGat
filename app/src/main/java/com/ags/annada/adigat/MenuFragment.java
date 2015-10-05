package com.ags.annada.adigat;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ags.annada.adigat.database.Food;
import com.ags.annada.adigat.database.FoodItemDataSource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Annada on 23/09/2015.
 */
public class MenuFragment extends ListFragment {
    private static final String TAG = "MenuFragment";

    //int color;
    public static List<FoodItem> mFoodItemsList;        // Food items list
    //String[] mFoodItems = {"Chicken","Lamb","Fish","Rice","Dal"};
    //double[] mPrice = {5.30,6.30,7.2,8.6,2.90};
    int[] mFoodPlates = new int[5];
    FoodAdapter1 mFoodAdapter;

    public MenuFragment(){

    }
/*
    public MenuFragment(int color) {
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

        //Initialize the food items list
        mFoodItemsList = new ArrayList<FoodItem>();
        Resources resources = getResources();

        //Get the food title retrive from DB
        //FoodItemDataSource foodItemDataSource = new FoodItemDataSource(getActivity());
        //foodItemDataSource.open();
        AdigetApp globalApp = (AdigetApp)(getActivity().getApplicationContext());
        globalApp.getFoodItemDataSource().open();
        List<Food> foodList = globalApp.getFoodItemDataSource().getAllFoodItems();

        /*
        mFoodItemsList.add(new FoodItem(mFoodPlates[0],mFoodItems[0],resources.getDrawable(R.mipmap.chicken,null),mPrice[0]));
        mFoodItemsList.add(new FoodItem(mFoodPlates[1],mFoodItems[1],resources.getDrawable(R.mipmap.lamb,null),mPrice[1]));
        mFoodItemsList.add(new FoodItem(mFoodPlates[2],mFoodItems[2],resources.getDrawable(R.mipmap.fish,null),mPrice[2]));
        mFoodItemsList.add(new FoodItem(mFoodPlates[3],mFoodItems[3],resources.getDrawable(R.mipmap.rice,null),mPrice[3]));
        mFoodItemsList.add(new FoodItem(mFoodPlates[4],mFoodItems[4],resources.getDrawable(R.mipmap.dal,null),mPrice[4]));
        */

        double foodPrice = ((double)foodList.get(0).getFoodPrice())/100;
        mFoodItemsList.add(new FoodItem(mFoodPlates[0],foodList.get(0).getFoodTitle(),resources.getDrawable(R.mipmap.chicken, null),foodPrice));

        foodPrice = ((double)foodList.get(1).getFoodPrice())/100;
        mFoodItemsList.add(new FoodItem(mFoodPlates[1],foodList.get(1).getFoodTitle(),resources.getDrawable(R.mipmap.lamb, null),foodPrice));

        foodPrice = ((double)foodList.get(2).getFoodPrice())/100;
        mFoodItemsList.add(new FoodItem(mFoodPlates[2],foodList.get(2).getFoodTitle(),resources.getDrawable(R.mipmap.fish, null),foodPrice));

        foodPrice = ((double)foodList.get(3).getFoodPrice())/100;
        mFoodItemsList.add(new FoodItem(mFoodPlates[3],foodList.get(3).getFoodTitle(),resources.getDrawable(R.mipmap.rice, null),foodPrice));

        foodPrice = ((double)foodList.get(4).getFoodPrice())/100;
        mFoodItemsList.add(new FoodItem(mFoodPlates[4],foodList.get(4).getFoodTitle(), resources.getDrawable(R.mipmap.dal,null),foodPrice));

        // initialize and set the list adapter
        mFoodAdapter = new FoodAdapter1(getActivity(),mFoodItemsList);
        setListAdapter(mFoodAdapter);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // remove the dividers from the ListView of the ListFragment
        getListView().setDivider(null);

        // Set list height.
        //ViewGroup.LayoutParams params = getListView().getLayoutParams();
        //params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        //getListView().setLayoutParams(params);
        //getListView().requestLayout();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        //mFoodPlates[position]++;
        //final FoodItem foodItem = (FoodItem)mFoodItemsList.get(position);
        //foodItem.mNoOfPlates = mFoodPlates[position];

        ((FoodItem)mFoodItemsList.get(position)).mNoOfPlates++;
        mFoodAdapter.notifyDataSetChanged();
    }

    public void updateView(){
        mFoodAdapter.notifyDataSetChanged();
    }
}
