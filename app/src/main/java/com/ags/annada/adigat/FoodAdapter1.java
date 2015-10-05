package com.ags.annada.adigat;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Annada on 22/09/2015.
 */
public class FoodAdapter1 extends ArrayAdapter<FoodItem> {
    private static final String TAG = "FoodAdapter1";

    Context context;
    int layoutResourceId;
    FoodItem data[] = null;

    public FoodAdapter1(Context context, List<FoodItem> data) {
        super(context, R.layout.food_item_row,data);
        //this.context = context;
        //this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FoodItemHolder holder = null;

        if(convertView == null){
            //LayoutInflater inflater = LayoutInflater.from(getContext());
            //convertView = inflater.inflate(R.layout.food_item_row, parent, false);

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.food_item_row, parent, false);

            holder = new FoodItemHolder();
            holder.txtNoOfPlates = (TextView)convertView.findViewById(R.id.noOfItems);
            holder.imgIcon = (ImageView)convertView.findViewById(R.id.foodtemIcon);
            holder.txtTitle = (TextView)convertView.findViewById(R.id.itemName);
            holder.txtPrice = (TextView)convertView.findViewById(R.id.price);

            convertView.setTag(holder);
        }
        else{
            holder = (FoodItemHolder)convertView.getTag();
        }

        //FoodItem foodItem = data[position];
        FoodItem foodItem = getItem(position);
        holder.txtNoOfPlates.setText(Integer.toString(foodItem.mNoOfPlates));
        holder.txtTitle.setText(foodItem.mFoodTitle);
        holder.txtPrice.setText("£" + Double.toString(foodItem.mPrice));
        holder.imgIcon.setImageDrawable(foodItem.mIcon);

        return convertView;
    }

    static class FoodItemHolder{
        TextView txtNoOfPlates;
        TextView txtTitle;
        ImageView imgIcon;
        TextView txtPrice;
    }
}
