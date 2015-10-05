package com.ags.annada.adigat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created by Annada on 19/09/2015.
 */

public class MenuFragment1 extends Fragment{
    private static final String TAG = "MenuFragment1";

    int color;
    ListView mListView;
    String[] mFoodItems = {"Chicken","Lamb","Fish","Rice","Dal"};
    double[] mPrice = {5.30,6.30,7,8,2.90};
    int[] mFoodPlates = new int[5];

    public MenuFragment1(int color) {
        this.color = color;
    }

    public MenuFragment1(){

    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        this.color = args.getInt("color");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.menu_fragment,container,false);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        FoodItem1 foodItemData[] = new FoodItem1[]{
                new FoodItem1(mFoodPlates[0],mFoodItems[0],R.mipmap.chicken,mPrice[0]),
                new FoodItem1(mFoodPlates[1],mFoodItems[1],R.mipmap.lamb,mPrice[1]),
                new FoodItem1(mFoodPlates[2],mFoodItems[2],R.mipmap.fish,mPrice[2]),
                new FoodItem1(mFoodPlates[3],mFoodItems[3],R.mipmap.rice,mPrice[3]),
                new FoodItem1(mFoodPlates[4],mFoodItems[4],R.mipmap.dal,mPrice[4])};

        final FoodAdapter1 foodAdapter = null;// = new FoodAdapter(getContext(),R.layout.food_item_row,foodItemData);

        mListView = (ListView)getView().findViewById(R.id.foodItems);
        mListView.setAdapter(foodAdapter);


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mFoodPlates[position]++;

                final FoodItem1 foodItem = (FoodItem1)parent.getItemAtPosition(position);

                foodItem.mNoOfPlates = mFoodPlates[position];
                foodAdapter.notifyDataSetChanged();
            }
        });


        //final FrameLayout frameLayout = (FrameLayout) rootView.findViewById(R.id.menufrag_bg);
        //frameLayout.setBackgroundColor(color);
    }
}
