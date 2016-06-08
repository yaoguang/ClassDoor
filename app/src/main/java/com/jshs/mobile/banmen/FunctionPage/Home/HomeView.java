package com.jshs.mobile.banmen.FunctionPage.Home;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.jshs.mobile.banmen.BaseContent.BaseLayer;

/**
 * Created by icezers on 16/6/8.
 */
public interface HomeView extends BaseLayer {

	FragmentManager getHomeFragmentManager();

	void setPagerAdapter(FragmentStatePagerAdapter adapter);

}
