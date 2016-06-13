package com.jshs.mobile.banmen.BaseContent;

import android.app.Activity;

/**
 * Created by popze on 2016/6/6.
 */
public abstract class BaseActivity extends Activity{

	public String TAG() {
		return getClass().getSimpleName(); 
	}
}
