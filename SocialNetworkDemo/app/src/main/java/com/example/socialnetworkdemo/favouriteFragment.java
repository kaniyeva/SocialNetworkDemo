package com.example.socialnetworkdemo;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class favouriteFragment extends Fragment implements FragmentInterface{

    RecyclerViewAdapter adapter;
    RecyclerView rc;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favourite, container, false);
        rc = view.findViewById(R.id.listFavour);

        LinearLayoutManager lc = new LinearLayoutManager(getActivity());
        rc.setLayoutManager(lc);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rc.getContext(),
                lc.getOrientation());
        rc.addItemDecoration(dividerItemDecoration);
        Log.e("ONCREATE","ONCREATE");

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("ONRESUME","ONRESUME");
        adapter = new RecyclerViewAdapter(getActivity(),MainActivity.favList);
        rc.setAdapter(adapter);
    }

    @Override
    public void fragmentBecameVisible() {
        Log.e("ONRESUME","ONRESUME");
        adapter = new RecyclerViewAdapter(getActivity(),MainActivity.favList);
        rc.setAdapter(adapter);
    }
}
