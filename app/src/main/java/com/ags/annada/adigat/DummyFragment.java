package com.ags.annada.adigat;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Annada on 18/09/2015.
 */
public class DummyFragment extends Fragment {
    int color;
    //SimpleRecyclerAdapter adapter;
    public DummyFragment(int color) {
        this.color = color;
    }

    public DummyFragment(){

    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        this.color = args.getInt("color");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dummy_fragment, container, false);
        final FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.dummyfrag_bg);
        frameLayout.setBackgroundColor(color);
        //RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.dummyfrag_scrollableview);
        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        //recyclerView.setLayoutManager(linearLayoutManager);
        //recyclerView.setHasFixedSize(true);
        List<String> list = new ArrayList<String>();
        //for (int i = 0; i < VersionModel.data.length; i++) {
        //    list.add(VersionModel.data[i]);
        //}
        //adapter = new SimpleRecyclerAdapter(list);
        //recyclerView.setAdapter(adapter);
        return view;
    }
}
