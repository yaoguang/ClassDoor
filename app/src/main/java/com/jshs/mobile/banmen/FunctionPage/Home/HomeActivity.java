package com.jshs.mobile.banmen.FunctionPage.Home;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.jshs.mobile.banmen.BaseContent.BaseFragmentActivity;
import com.jshs.mobile.banmen.R;
import com.jshs.mobile.banmen.Tools.MLOG_LEVEL;
import com.jshs.mobile.banmen.Tools.MLog;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

;


@ContentView(R.layout.home_activity)
public class HomeActivity extends BaseFragmentActivity implements HomeView, View.OnClickListener {


    @ViewInject(R.id.home_viewpager)
    public ViewPager _viewPager;

    @ViewInject(R.id.home_bar_item1)
    public View _homeBarItem1;

    @ViewInject(R.id.home_bar_item2)
    public View _homeBarItem2;

    @ViewInject(R.id.home_bar_item3)
    public View _homeBarItem3;

    @ViewInject(R.id.home_bar_item4)
    public View _homeBarItem4;

    @ViewInject(R.id.home_bar_item5)
    public View _homeBarItem5;


    private int[] barItemIds = {R.id.home_bar_item1, R.id.home_bar_item2, R.id.home_bar_item3, R.id.home_bar_item4, R.id.home_bar_item5};
    private ArrayList<View> barItems = new ArrayList<>();

    private HomePresenter _presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        _presenter = new HomePresenter(this);
        _presenter.InitPagers();

        for (int i = 0; i < barItemIds.length; i++) {
            View view = findViewById(barItemIds[i]);
            view.setTag(i);
            view.setOnClickListener(this);
            barItems.add(view);
        }
    }


    public void onClick(View view) {
        _viewPager.setCurrentItem((int) view.getTag());
    }

    private void selectPager(int position) {
        for (int i = 0; i < barItems.size(); i++) {
            barItems.get(i).setSelected(position == i);
        }
        try {
            _presenter.onPagerSelect(position);
        } catch (Exception e) {
            MLog.print(TAG(), MLOG_LEVEL.E, e.toString());
        }
    }

    @Override
    public FragmentManager getHomeFragmentManager() {
        return getSupportFragmentManager();
    }

    @Override
    public void setPagerAdapter(FragmentStatePagerAdapter adapter) {
        _viewPager.setAdapter(adapter);
        _viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                selectPager(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    selectPager(0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 100);
    }


}
