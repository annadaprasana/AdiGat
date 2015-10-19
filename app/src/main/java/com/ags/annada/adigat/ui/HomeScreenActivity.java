package com.ags.annada.adigat.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ags.annada.adigat.R;
import com.ags.annada.adigat.events.DBOperationFinished;
import com.ags.annada.adigat.events.JsonOperationFinished;
import com.ags.annada.adigat.json.FoodFactory;
import com.ags.annada.adigat.json.FoodModel;
import com.ags.annada.adigat.receiver.ParseJsonResultReceiver;
import com.ags.annada.adigat.services.ParseJsonService;
import com.ags.annada.adigat.services.StoreInDBService;
import com.google.android.gms.plus.Plus;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by Annada on 18/09/2015.
 */
public class HomeScreenActivity extends AppCompatActivity
                                implements ParseJsonResultReceiver.Receiver{
    private static final String TAG = HomeScreenActivity.class.getSimpleName();

    //private Settings mSettings;
    private ViewPagerAdapter mPagerAdapter;
    private ParseJsonResultReceiver mReceiver;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);

        mReceiver = new ParseJsonResultReceiver(new Handler());
        mReceiver.setReceiver(this);
        startParseJsonService();

        ViewPager viewPager = (ViewPager) findViewById(R.id.tabanim_viewpager);
        setupViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                Log.d(TAG, "Page Selected:-" + Integer.toString(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabanim_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().registerSticky(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void onEventMainThread(DBOperationFinished event){
        if(event.getStatus().equalsIgnoreCase("success")) {
            Log.i(TAG, "DB operation Finished");
            //MenuFragment menuFragment = (MenuFragment) mPagerAdapter.getItem(0);
            //menuFragment.initLoader();

            //OrderFragment orderFragment = (OrderFragment) mPagerAdapter.getItem(1);
            //orderFragment.initLoader();
        }
    }

    public void onEventMainThread(JsonOperationFinished event){
        if(event.getStatus().equalsIgnoreCase("success")) {
            Log.i(TAG, "JSON success");

            String jsonString = event.getResult();

            try {
                ArrayList<FoodModel> foodModels = FoodFactory.decodeJson(new JSONArray(jsonString));

                Intent storeDBIntent = new Intent(getApplicationContext(), StoreInDBService.class);
                storeDBIntent.putExtra("foods",foodModels);
                //storeDBIntent.putExtra("receiver",mReceiver);
                startService(storeDBIntent);
            } catch (JSONException e) {
                Log.e(TAG,e.getMessage());
            }

            //MenuFragment menuFragment = (MenuFragment) mPagerAdapter.getItem(0);
            //menuFragment.initLoader();

            //OrderFragment orderFragment = (OrderFragment) mPagerAdapter.getItem(1);
            //orderFragment.initLoader();
        }else if(event.getStatus().equalsIgnoreCase("failed")){
            Log.e(TAG, "JSON failed");
        }
    }

    private void startParseJsonService(){
        Intent jsonServiceIntent = new Intent(getApplicationContext(),ParseJsonService.class);
        //jsonServiceIntent.putExtra("receiver",mReceiver);
        startService(jsonServiceIntent);
    }

    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {
        /*
        switch(resultCode){
            case ParseJsonService.JSON_STATUS_FINISHED:
            {
                String jsonString = resultData.getString("json");

                try {
                    ArrayList<FoodModel> foodModels = FoodFactory.decodeJson(new JSONArray(jsonString));

                    Intent storeDBIntent = new Intent(getApplicationContext(), StoreInDBService.class);
                    storeDBIntent.putExtra("foods",foodModels);
                    //storeDBIntent.putExtra("receiver",mReceiver);
                    startService(storeDBIntent);
                } catch (JSONException e) {
                    Log.e(TAG,e.getMessage());
                }

                //Store in Database in another service
            }
            break;
            case ParseJsonService.JSON_STATUS_ERROR:
            {
                String error = resultData.getString(Intent.EXTRA_TEXT);
                Toast.makeText(getApplicationContext(),error,Toast.LENGTH_SHORT);
            }
            break;
            case StoreInDBService.DB_STATUS_FINISHED:
            {
                Log.i(TAG,"DB operation Finished");
                MenuFragment menuFragment = (MenuFragment)mPagerAdapter.getItem(0);
                menuFragment.initLoader();

                OrderFragment orderFragment = (OrderFragment)mPagerAdapter.getItem(1);
                orderFragment.initLoader();
            }
            break;
        }
        */
    }

    public void logout(View view){
        //TODO implement logic for logout operation.
        //Both Google+ and without social logout
        if (LoginScreenActivity.mGoogleApiClient.isConnected()) {
            Plus.AccountApi.clearDefaultAccount(LoginScreenActivity.mGoogleApiClient);
            LoginScreenActivity.mGoogleApiClient.disconnect();
        }
        startActivity(new Intent(HomeScreenActivity.this, LoginScreenActivity.class));
    }

    private void setupViewPager(ViewPager viewPager) {
        mPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        mPagerAdapter.addFrag(new MenuFragment(),"Menu");
        mPagerAdapter.addFrag(new OrderFragment(),"Order");
        viewPager.setAdapter(mPagerAdapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }
        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
