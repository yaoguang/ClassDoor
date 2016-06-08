package com.jshs.mobile.banmen.FunctionPage.Home;

import com.jshs.mobile.banmen.BaseContent.BaseHomePager;
import com.jshs.mobile.banmen.BaseContent.BasePresenter;
import com.jshs.mobile.banmen.FunctionPage.AbleManPager.AbleManFragment;
import com.jshs.mobile.banmen.FunctionPage.GalleryPager.GalleryFragment;
import com.jshs.mobile.banmen.FunctionPage.IMPager.IMFragment;
import com.jshs.mobile.banmen.FunctionPage.MinePager.MineFragment;
import com.jshs.mobile.banmen.FunctionPage.ServicePager.ServiceFragment;

/**
 * Created by icezers on 16/6/8.
 */
public class HomePresenter extends BasePresenter {

	private HomeView _homeView;

	private HomePagerAdapter _pagerAdapter;

	private ServiceFragment _serviceFragment;
	private GalleryFragment _galleryFragment;
	private IMFragment _imFragment;
	private AbleManFragment _ableManFragment;
	private MineFragment _mineFragment;

	public HomePresenter(HomeView homeView) {
		_homeView = homeView;
	}

	public void InitPagers() {
		_serviceFragment = new ServiceFragment();
		_galleryFragment = new GalleryFragment();
		_imFragment = new IMFragment();
		_ableManFragment = new AbleManFragment();
		_mineFragment = new MineFragment();


		_pagerAdapter = new HomePagerAdapter(_homeView.getHomeFragmentManager());
		_pagerAdapter.addPager(_serviceFragment);
		_pagerAdapter.addPager(_galleryFragment);
		_pagerAdapter.addPager(_imFragment);
		_pagerAdapter.addPager(_ableManFragment);
		_pagerAdapter.addPager(_mineFragment);

		_homeView.setPagerAdapter(_pagerAdapter);
	}


	public void onPagerSelect(int position) throws Exception {
		((BaseHomePager) _pagerAdapter.getItem(position)).onPagerSelect();
	}

}
