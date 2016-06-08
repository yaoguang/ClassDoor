package com.jshs.mobile.banmen.FunctionPage.StartPager;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.WindowManager;

import com.jshs.mobile.banmen.BaseContent.BaseFragmentActivity;
import com.jshs.mobile.banmen.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

/**
 * Created by SZH on 2016/6/8.
 */
@ContentView(R.layout.login_activity)
public class LoginAndRegistActivity extends BaseFragmentActivity {
    @ViewInject(R.id.login)
    private View loginTag;

    @ViewInject(R.id.regist)
    private View registTag;

    @ViewInject(R.id.viewpager)
    private ViewPager viewPager;

    private ArrayList<Fragment> fragments = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        x.view().inject(this);

        initDates();
        initAction();
    }

    private void initDates() {
        fragments.add(new LoginFragment());
        fragments.add(new RegistFragment());
    }

    private void initAction() {
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                selectPager(position);
            }
        });
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });

        selectPager(0);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }

    @Event(value = {R.id.login, R.id.regist})
    private void selectPager(View view) {
        selectPager(view.getId() == R.id.login ? 0 : 1);
    }

    private void selectPager(int position) {
        loginTag.setSelected(position == 0);
        registTag.setSelected(position == 1);
        viewPager.setCurrentItem(position);
    }
}
