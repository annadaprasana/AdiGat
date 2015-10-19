package com.ags.annada.adigat.json;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Annada on 09/10/2015.
 */
public class FoodFactory {
    private static final String TAG = FoodFactory.class.getSimpleName();

    private static final String JSON_FOOD_TITLE_KEY = "title";
    private static final String JSON_FOOD_PRICE_KEY = "price";
    private static final String JSON_FOOD_IMAGE_KEY = "image";
    private static final String JSON_FOOD_DESC_KEY = "desc";

    //Decode Food json into Food Model
    private static FoodModel decodeJson(JSONObject foodJson){
        FoodModel foodModel = new FoodModel();

        try {
            foodModel.setTitle(foodJson.getString(JSON_FOOD_TITLE_KEY));
            foodModel.setPrice(foodJson.getDouble(JSON_FOOD_PRICE_KEY));
            foodModel.setImage(foodJson.getString(JSON_FOOD_IMAGE_KEY));
            foodModel.setDesc(foodJson.getString(JSON_FOOD_DESC_KEY));
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG,e.getMessage());
            return null;
        }

        return foodModel;
    }

    //Decodes json results into arry of food model objects
    public static ArrayList<FoodModel> decodeJson(JSONArray foodJsonArray){
        ArrayList<FoodModel> foodModels = new ArrayList<FoodModel>(foodJsonArray.length());

        JSONObject foodJson;
        FoodModel foodModel;

        for(int index=0; index < foodJsonArray.length(); index++ ){
            try {
                foodJson = foodJsonArray.getJSONObject(index);

                foodModel = decodeJson(foodJson);

                if(foodModel != null){
                    foodModels.add(decodeJson(foodJson));
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e(TAG,e.getMessage());
                continue;
            }
        }

        return foodModels;
    }
}
