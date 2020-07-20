package com.example.practiceproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.practiceproject.fragment.Fragment_1;
import com.example.practiceproject.fragment.Fragment_2;
import com.example.practiceproject.fragment.Fragment_3;
import com.example.practiceproject.fragment.Fragment_4;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import static androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    SectionPageAdapter adapter = new SectionPageAdapter(getSupportFragmentManager(),  BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        viewPager = findViewById(R.id.viewPager); // 뷰페이저
        setupViewPager(viewPager);  // 뷰페이저 -> 아답터

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager); // 탭레이아웃 -> 뷰페이저 -> 아답터

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchFragment();
            }
        });


    }

    public void setupViewPager(ViewPager viewPager){
        adapter.addFragment(new Fragment_1(), "첫번째");
        adapter.addFragment(new Fragment_2(), "두번째");
        viewPager.setAdapter(adapter);
    }

    private boolean isFragment3 = true ;
    public void switchFragment() {
        Fragment fr;
        if (isFragment3) {fr = new Fragment_3() ;}
        else {fr = new Fragment_4() ;}
        isFragment3 = !isFragment3;
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fr);
        fragmentTransaction.commit();
    }
}