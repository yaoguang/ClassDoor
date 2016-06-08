package com.jshs.mobile.banmen.FunctionPage.Blank;

import android.content.Intent;
import android.os.Bundle;

import com.jshs.mobile.banmen.BaseContent.BaseActivity;
import com.jshs.mobile.banmen.FunctionPage.Home.HomeActivity;

/**
 * Created by icezers on 16/6/8.
 */
public class BlankActivity extends BaseActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);


		startActivity(new Intent(this, HomeActivity.class));
		finish();
	}
}
