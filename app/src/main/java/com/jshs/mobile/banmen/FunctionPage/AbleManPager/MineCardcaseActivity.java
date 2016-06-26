package com.jshs.mobile.banmen.FunctionPage.AbleManPager;

import android.content.Intent;
import android.view.View;

import com.jshs.mobile.banmen.BaseContent.XBaseActivity;
import com.jshs.mobile.banmen.R;
import com.jshs.mobile.banmen.Tools.TitleHolder;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;

/**
 * Created by SZH on 2016/6/25.
 */
@ContentView(R.layout.mine_cardcase_activity)
public class MineCardcaseActivity extends XBaseActivity {
    @Override
    protected void initViews() {
        titleHolder = TitleHolder.initSimpleTitle(this, R.string.my_cardcase);
    }

    @Event(value = R.id.mine_card)
    private void onClick(View view) {
        startBusinessCardActivity();
    }

    private void startBusinessCardActivity() {
        Intent intent = new Intent(MineCardcaseActivity.this, BusinessCardActivity.class);
        intent.putExtra("card", "");
        startActivity(intent);
    }
}
