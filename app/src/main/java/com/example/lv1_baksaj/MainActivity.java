package com.example.lv1_baksaj;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lv1_baksaj.fragments.PageFragment1;
import com.example.lv1_baksaj.fragments.PageFragment2;
import com.example.lv1_baksaj.fragments.PageFragment3;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PageFragment1.PersonalInfoListener, PageFragment2.StudentInfoListener{

    public static ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //////////////////////////////////////////////////
        viewPager = findViewById(R.id.vpPager);
        viewPager.setOffscreenPageLimit(2);

        FragmentAdapter viewPageAdapter = new FragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPageAdapter);
    }


    @Override
    public void onPersonalInfoSent(String ime, String prezime, String datum) {
        String tag = "android:switcher:" + R.id.vpPager + ":" + 2;
        PageFragment3 fragment = (PageFragment3) getSupportFragmentManager().findFragmentByTag(tag);
        fragment.updatePersonalInfo(ime, prezime, datum);
    }

    @Override
    public void onStudentInfoSent(String predmet, String ime_profesora, String akademska_godina, String sati_predavanja, String sati_LV) {
        String tag = "android:switcher:" + R.id.vpPager + ":" + 2;
        PageFragment3 fragment = (PageFragment3) getSupportFragmentManager().findFragmentByTag(tag);
        fragment.updateStudentInfo(predmet, ime_profesora, akademska_godina, sati_predavanja, sati_LV);
    }

    public static void setCurrentItem (int item, boolean smoothScroll) {
        viewPager.setCurrentItem(item, smoothScroll);
    }
}