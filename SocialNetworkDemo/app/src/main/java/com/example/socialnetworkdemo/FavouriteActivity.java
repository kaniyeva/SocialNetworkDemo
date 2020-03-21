package com.example.socialnetworkdemo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FavouriteActivity extends AppCompatActivity {


    RecyclerViewAdapter adapter;
    RecyclerView rc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);


        rc = findViewById(R.id.favouriteList);

        LinearLayoutManager lc = new LinearLayoutManager(this);
        rc.setLayoutManager(lc);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rc.getContext(),
                lc.getOrientation());
        rc.addItemDecoration(dividerItemDecoration);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter = new RecyclerViewAdapter(this,MainActivity.favList);
        rc.setAdapter(adapter);
    }
}
