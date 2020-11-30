package com.example.lv1_baksaj;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.lv1_baksaj.fragments.PageFragment1;
import com.example.lv1_baksaj.fragments.PageFragment2;
import com.example.lv1_baksaj.fragments.PageFragment3;

public class FragmentAdapter extends FragmentPagerAdapter{
    private static int NUM_ITEMS = 3;

    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new PageFragment1();
            case 1:
                return  new PageFragment2();
            case 2:
                return  new PageFragment3();
            default:
                return null;
        }
    }
    @Override
    public CharSequence getPageTitle(int position){
        return "Page "+ position;
    }

}
