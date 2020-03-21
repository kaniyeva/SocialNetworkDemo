package com.example.socialnetworkdemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class TweetsFragment extends Fragment implements FragmentInterface{


    ImageView favouriteButton;
    ViewPager viewPager;
    TabLayout tabLayout;
    RecyclerViewAdapter adapter;
    int state = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view123 = inflater.inflate(R.layout.fragment_tweets, container, false);
        RecyclerView rc = view123.findViewById(R.id.listTxeets);
        LinearLayoutManager lc = new LinearLayoutManager(getActivity());
        rc.setLayoutManager(lc);
        adapter = new RecyclerViewAdapter(getActivity(),MainActivity.list);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rc.getContext(),
                lc.getOrientation());
        rc.addItemDecoration(dividerItemDecoration);
        rc.setAdapter(adapter);
        rc.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                state = newState;
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        if(state != 0){
            rc.setVerticalScrollbarPosition(state);
        }
        return view123;
    }


    @Override
    public void fragmentBecameVisible() {

    }
}
