package com.jshs.mobile.banmen.FunctionPage.AbleManPager;

import android.widget.ListView;

import com.jshs.mobile.banmen.BaseContent.XBaseActivity;
import com.jshs.mobile.banmen.Models.AbleMan.AbleMan;
import com.jshs.mobile.banmen.R;
import com.jshs.mobile.banmen.Tools.TitleHolder;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

/**
 * Created by SZH on 2016/6/25.
 */
@ContentView(R.layout.same_friend_activity)
public class SameFriendActivity extends XBaseActivity {
    @ViewInject(R.id.listview)
    private ListView listView;
    private ArrayList<AbleMan> datas = new ArrayList<>();
    private BaseUserinfoAdapter<AbleMan> adapter;

    @Override
    protected void initViews() {
        titleHolder = TitleHolder.initSimpleTitle(this, R.string.same_friend);
    }

    public void initAction() {
        adapter = new BaseUserinfoAdapter(this, null, datas);
        listView.setAdapter(adapter);
    }
}
