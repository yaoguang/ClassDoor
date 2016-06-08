package com.jshs.mobile.banmen.FunctionPage.Home;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;

import com.jshs.mobile.banmen.BaseContent.BaseFragmentActivity;
import com.jshs.mobile.banmen.R;
import com.jshs.mobile.banmen.Tools.MLOG_LEVEL;
import com.jshs.mobile.banmen.Tools.MLog;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

;


@ContentView(R.layout.home_activity)
public class HomeActivity extends BaseFragmentActivity implements HomeView {


	@ViewInject(R.id.home_viewpager)
	public ViewPager _viewPager;

	@ViewInject(R.id.home_bar_layout_item1)
	public LinearLayout _homeBarItem1;

	@ViewInject(R.id.home_bar_layout_item2)
	public LinearLayout _homeBarItem2;

	@ViewInject(R.id.home_bar_layout_item3)
	public LinearLayout _homeBarItem3;

	@ViewInject(R.id.home_bar_layout_item4)
	public LinearLayout _homeBarItem4;

	@ViewInject(R.id.home_bar_layout_item5)
	public LinearLayout _homeBarItem5;


	private HomePresenter _presenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		x.view().inject(this);
		_presenter = new HomePresenter(this);
		_presenter.InitPagers();

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
				try {
					_presenter.onPagerSelect(position);
				} catch (Exception e) {
					MLog.print(TAG(), MLOG_LEVEL.E,e.toString());
				}
			}

			@Override
			public void onPageScrollStateChanged(int state) {

			}
		});
		try {
			_presenter.onPagerSelect(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
