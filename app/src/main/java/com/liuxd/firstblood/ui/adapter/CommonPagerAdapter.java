package com.liuxd.firstblood.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Liuxd on 2016/11/22 10:11.
 * 通用的 PagerAdapter
 */

public class CommonPagerAdapter<T extends Fragment> extends FragmentPagerAdapter {
    private String[] mTypes;
    private List<T> mFragments;

    public CommonPagerAdapter(FragmentManager fm, List<T> fragments, String[] types) {
        super(fm);
        this.mFragments = fragments;
        this.mTypes = types;
    }

    @Override
    public T getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mTypes.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTypes[position];
    }
}
