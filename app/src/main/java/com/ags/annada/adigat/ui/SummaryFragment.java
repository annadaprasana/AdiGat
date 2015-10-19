package com.ags.annada.adigat.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ags.annada.adigat.R;

/**
 * Created by Annada on 19/09/2015.
 */
public class SummaryFragment extends Fragment {
    private static final String TAG = SummaryFragment.class.getSimpleName();

    //int color;

    public SummaryFragment(){
    }

    /*
    public SummaryFragment(int color) {
        this.color = color;
    }


    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        this.color = args.getInt("color");
    }
*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.summary_fragment,container,false);
        //final FrameLayout frameLayout = (FrameLayout) rootView.findViewById(R.id.summaryfrag_bg);
        //frameLayout.setBackgroundColor(color);

        return rootView;
    }

    public void updateView(){

    }
}
