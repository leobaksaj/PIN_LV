package com.example.lv1_baksaj;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lv1_baksaj.fragments.PageFragment3;
import com.example.lv1_baksaj.fragments.PageFragment2;
import com.example.lv1_baksaj.fragments.PageFragment1;

public class MyAdapter extends FragmentPagerAdapter {
    private static int NUM_ITEMS = 3;

    public MyAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    // Returns total number of pages
    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    // Returns the fragment to display for that page
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                return new PageFragment1();
            case 1: // Fragment # 0 - This will show FirstFragment different title
                return new PageFragment2();
            case 2: // Fragment # 1 - This will show SecondFragment
                return new PageFragment3();
            default:
                return null;
        }
    }

}
