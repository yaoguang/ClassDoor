package com.jshs.mobile.banmen.FunctionPage.Home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.jshs.mobile.banmen.BaseContent.BaseFragment;

import java.util.ArrayList;

/**
 * Created by icezers on 16/6/8.
 */
public class HomePagerAdapter extends FragmentStatePagerAdapter {

	private ArrayList<BaseFragment> _pagers;


	public HomePagerAdapter(FragmentManager fm) {
		super(fm);
		_pagers = new ArrayList<>();
	}

	public void addPager(BaseFragment pager) {
		_pagers.add(pager);
	}

	@Override
	public Fragment getItem(int position) {
		return _pagers.get(position);
	}

	@Override
	public int getCount() {
		return _pagers == null ? 0 : _pagers.size();
	}
}
