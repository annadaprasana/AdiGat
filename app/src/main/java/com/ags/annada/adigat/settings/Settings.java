package com.ags.annada.adigat.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Annada on 10/10/2015.
 */
public class Settings {
    private static final String TAG = Settings.class.getSimpleName();

    private static Settings mInstance = null;

    private final SharedPreferences mSharedPreference;
    private static final String DB_CREATED_SETTINGS_KEY = "db_created_setting_key";

    private Settings(final Context context){
        mSharedPreference = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static Settings getInstance(final Context context){
        if(mInstance == null){
            mInstance  = new Settings(context);
        }

        return mInstance;
    }

    public void setDBCreatedFlag(boolean dbCreatedFlag){
        mSharedPreference.edit().putBoolean(DB_CREATED_SETTINGS_KEY, dbCreatedFlag).apply();
    }

    public boolean getDBCreatedFlag(){
        return  mSharedPreference.getBoolean(DB_CREATED_SETTINGS_KEY, false);
    }
}
