package com.example.clienthttp.treatment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        //Here you can set what is displayed on the fragment e.g. check for index i and if it is 0 do something
        MesurementsFragment fragment = new MesurementsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("text","Mesurement "+ ++i);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        //return how many is necessary
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return "Tab "+ ++position;
    }
}
