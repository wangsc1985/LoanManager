package com.wangsc.loanmanager.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.wangsc.loanmanager.R;
import com.wangsc.loanmanager.fragment.ActionBarFragment;
import com.wangsc.loanmanager.fragment.AreaFragment;
import com.wangsc.loanmanager.fragment.HomeFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    // 视图变量
    private ViewPager mViewPager;
    private BottomNavigationView navigation;
    // 类变量
    // 值变量
    private List<Fragment> fragmentList;
    private ViewPagerAdapter mViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, ActionBarFragment.newInstance()).commit();

            getSupportActionBar().hide();
            navigation = (BottomNavigationView) findViewById(R.id.navigation);
            navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.navigation_loan:
                            mViewPager.setCurrentItem(0);
                            return true;
                        case R.id.navigation_statistics:
                            mViewPager.setCurrentItem(1);
                            return true;
                    }
                    return false;
                }
            });



            fragmentList = new ArrayList<>();
            fragmentList.add(new HomeFragment());
            fragmentList.add(new AreaFragment());

            mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
            mViewPager = (ViewPager) findViewById(R.id.viewPage_content);
            mViewPager.setAdapter(mViewPagerAdapter);
            mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    for(int i=0;i<navigation.getMenu().size();i++){
                        navigation.getMenu().getItem(i).setChecked(false);
                    }
                    navigation.getMenu().getItem(position).setChecked(true);
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                    switch (state) {
                        case ViewPager.SCROLL_STATE_IDLE:
                            break;
                        case ViewPager.SCROLL_STATE_DRAGGING:
                            break;
                        case ViewPager.SCROLL_STATE_SETTLING:
                            break;
                        default:
                            break;
                    }
                }
            });


        } catch (Exception e) {
            Snackbar.make(findViewById(R.id.container),e.getMessage(),Snackbar.LENGTH_LONG);
        }
    }


    public class ViewPagerAdapter extends FragmentPagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }
}
