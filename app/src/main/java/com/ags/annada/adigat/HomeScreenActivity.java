package com.ags.annada.adigat;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.android.gms.plus.Plus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Annada on 18/09/2015.
 */
public class HomeScreenActivity extends AppCompatActivity {
    private static final String TAG = "HomeScreenActivity";
    ViewPagerAdapter mPagerAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);

        ViewPager viewPager = (ViewPager) findViewById(R.id.tabanim_viewpager);
        setupViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.d(TAG, "Page Selected:-" + Integer.toString(position));

                switch(position){
                    case 0:
                    {
                        ((MenuFragment)mPagerAdapter.getItem(position)).updateView();
                    }
                    break;
                    case 1:
                    {
                        ((OrderFragment)mPagerAdapter.getItem(position)).updateView();
                    }
                    break;
                    case 2:
                    {
                        ((SummaryFragment)mPagerAdapter.getItem(position)).updateView();
                    }
                    break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabanim_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    public void logout(View view){
        //TODO implement logic for logout operation.
        //Both Google+ and without social logout
        if (LoginScreenActivity.mGoogleApiClient.isConnected()) {
            Plus.AccountApi.clearDefaultAccount(LoginScreenActivity.mGoogleApiClient);
            LoginScreenActivity.mGoogleApiClient.disconnect();
        }
        startActivity(new Intent(HomeScreenActivity.this,LoginScreenActivity.class));
    }

    private void setupViewPager(ViewPager viewPager) {
        mPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        mPagerAdapter.addFrag(new MenuFragment(),"Menu");
        mPagerAdapter.addFrag(new OrderFragment(),"Order");
        mPagerAdapter.addFrag(new SummaryFragment(),"Summary");
        //viewPagerAdapter.addFrag(new MenuFragment(getResources().getColor(R.color.accent_material_light,null)), "Menu");
        //viewPagerAdapter.addFrag(new OrderFragment(getResources().getColor(R.color.ripple_material_light,null)), "Order");
        //viewPagerAdapter.addFrag(new SummaryFragment(getResources().getColor(R.color.button_material_light,null)), "Summary");
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
