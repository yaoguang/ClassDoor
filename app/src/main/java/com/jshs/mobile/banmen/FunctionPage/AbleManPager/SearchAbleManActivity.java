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
@ContentView(R.layout.search_ableman_activity)
public class SearchAbleManActivity extends XBaseActivity {
    @Override
    protected void initViews() {
        titleHolder = TitleHolder.initSimpleTitle(this, R.string.search_anleman);
    }

    @Event(value = {R.id.search, R.id.add_to_link})
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.search:
                startActivity(new Intent(this, SearchResultActivity.class));
                break;
            case R.id.add_to_link:
                break;
        }
    }
}
