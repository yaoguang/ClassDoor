package com.jshs.mobile.banmen.FunctionPage.AbleManPager;

import android.view.View;
import android.widget.Toast;

import com.jshs.mobile.banmen.BaseContent.XBaseActivity;
import com.jshs.mobile.banmen.R;
import com.jshs.mobile.banmen.Tools.TitleHolder;

import org.xutils.view.annotation.ContentView;

/**
 * Created by SZH on 2016/6/26.
 */
@ContentView(R.layout.net_user_fei_activity)
public class NetUserFeiActivity extends XBaseActivity {

    @Override
    protected void initViews() {
        titleHolder = new TitleHolder(this, R.string.net_user_fei);
        titleHolder.initBtns(R.drawable.iconfont_plus, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NetUserFeiActivity.this, "显示菜单弹窗", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
