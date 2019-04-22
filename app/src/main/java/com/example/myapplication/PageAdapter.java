package com.example.myapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.myapplication.fragment.FragmentApp;
import com.example.myapplication.fragment.FragmentBath;
import com.example.myapplication.fragment.FragmentMode;
import com.example.myapplication.fragment.FragmentSetBluetooth;

/**
 * Created by abdalla on 2/18/18.
 */

public class PageAdapter extends FragmentPagerAdapter {

    private int numOfTabs;

    PageAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FragmentSetBluetooth();
            case 1:
                return new FragmentApp();
            case 2:
                return new FragmentMode();
            case 3:
                return new FragmentBath();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
