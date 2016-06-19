package com.jshs.mobile.banmen.FunctionPage.Blank;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.jshs.mobile.banmen.BaseContent.BaseActivity;
import com.jshs.mobile.banmen.FunctionPage.Home.HomeActivity;
import com.jshs.mobile.banmen.FunctionPage.StartPager.LoginAndRegistActivity;
import com.jshs.mobile.banmen.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.x;

/**
 * Created by icezers on 16/6/8.
 */

@ContentView(R.layout.blank_activity)
public class BlankActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(BlankActivity.this, LoginAndRegistActivity.class));
                finish();

            }
        }, 2000);
    }
}
