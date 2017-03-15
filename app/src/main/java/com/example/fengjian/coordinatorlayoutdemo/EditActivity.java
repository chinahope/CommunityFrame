package com.example.fengjian.coordinatorlayoutdemo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.belibrary.ByeBurgerBehavior;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fengjian on 2017/3/14.
 */

public class EditActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ViewPager mViewPager;
    private List<Fragment> fragmentList;
    private BottomNavigationView mNavigationView;
    private FloatingActionButton mFloatButton;
    private ByeBurgerBehavior mBehavior;
    private Toolbar mToolbar;

    public static void startActivity(Context context){
        Intent intent = new Intent();
        intent.setClass(context, EditActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        initData();
        initView();
    }
    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setTitle("Bye! Bye! Burger");
        setSupportActionBar(mToolbar);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mNavigationView = (BottomNavigationView) findViewById(R.id.bye_burger);
        mFloatButton = (FloatingActionButton) findViewById(R.id.floatButton);

        mBehavior = ByeBurgerBehavior.from(mFloatButton);

        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override public int getCount() {
                return fragmentList.size();
            }
        });


        mNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        if(item.getTitle().equals("home")){
                            mBehavior.show();
                            mViewPager.setCurrentItem(0);
                        }else if(item.getTitle().equals("search")){
                            ByeBurgerBehavior.from(mToolbar).hide();
                            mBehavior.hide();
                            mViewPager.setCurrentItem(1);
                        }else if(item.getTitle().equals("me")){

                            mBehavior.hide();
                            mViewPager.setCurrentItem(2);
                        }else if(item.getTitle().equals("setting")){
                            mBehavior.hide();
                            mViewPager.setCurrentItem(3);
                        }
                        return false;
                    }
                });

    }

    private void initData() {
        fragmentList = new ArrayList<>();
        fragmentList.add(HomeFragment.newInstance());

        fragmentList.add(HolderFragment.newInstance("search"));

        fragmentList.add(HolderFragment.newInstance("me"));

        fragmentList.add(HolderFragment.newInstance("setting"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
