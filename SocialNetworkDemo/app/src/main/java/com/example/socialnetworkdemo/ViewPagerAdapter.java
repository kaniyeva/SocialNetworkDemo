package com.example.socialnetworkdemo;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private String[] tabTitles=new String[]{"Tweets","Favourite"};


    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: return new TweetsFragment();

            case 1: return new favouriteFragment();
        }
        return null;
    }
    @Override
    public CharSequence getPageTitle(int position){
        return tabTitles[position];
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }
}
