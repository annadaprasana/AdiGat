package com.ags.annada.adigat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Annada on 24/09/2015.
 */
public class OrderAdapter extends ArrayAdapter<OrderItem> {
    private static final String TAG = "OrderAdapter";

    public OrderAdapter(Context context, List<OrderItem> data) {
        super(context, R.layout.order_item_row,data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        OrderItemHolder holder = null;

        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.order_item_row, parent, false);

            holder = new OrderItemHolder();
            holder.tvFoodTitle = (TextView)convertView.findViewById(R.id.foodTitle);
            holder.tvNoOfPlates = (TextView)convertView.findViewById(R.id.noOfPlates);
            holder.ivImgIcon = (ImageView)convertView.findViewById(R.id.foodItemIcon);

            convertView.setTag(holder);
        }else{
            holder = (OrderItemHolder)convertView.getTag();
        }

        OrderItem orderItem = getItem(position);
        holder.tvFoodTitle.setText(orderItem.mFoodTitle);
        holder.tvNoOfPlates.setText(Integer.toString(orderItem.mNoOfPlates));
        holder.ivImgIcon.setImageDrawable(orderItem.mIcon);

        return convertView;
    }

    static class OrderItemHolder{
        TextView tvFoodTitle;
        TextView tvNoOfPlates;
        ImageView ivImgIcon;
    }
}
