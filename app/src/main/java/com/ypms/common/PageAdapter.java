package com.ypms.common;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Hero on 2018/3/2.
 */

public class PageAdapter extends FragmentPagerAdapter {
    private Fragment[] mFragments;

    public PageAdapter(FragmentManager manager, Fragment[] fragments) {
        super(manager);
        this.mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments[position];
    }

    @Override
    public int getCount() {
        return mFragments.length;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return "";
    }

}
