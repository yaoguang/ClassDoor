package com.jshs.mobile.banmen.FunctionPage.MinePager;

import android.widget.ListView;

import com.jshs.mobile.banmen.BaseContent.XBaseActivity;
import com.jshs.mobile.banmen.FunctionPage.AbleManPager.BaseUserinfoAdapter;
import com.jshs.mobile.banmen.Models.AbleMan.AbleMan;
import com.jshs.mobile.banmen.R;
import com.jshs.mobile.banmen.Tools.TitleHolder;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

/**
 * Created by SZH on 2016/6/19.
 */
@ContentView(R.layout.simple_list_activity)
public class MineFansActivity extends XBaseActivity {
    @ViewInject(R.id.listview)
    private ListView listview;
    private ArrayList<AbleMan> datas = new ArrayList<>();
    private BaseUserinfoAdapter<AbleMan> adapter;

    @Override
    protected void initViews() {
        titleHolder = TitleHolder.initSimpleTitle(this, R.string.my_fans);
    }

    public void initAction() {
        adapter = new BaseUserinfoAdapter<>(this, getString(R.string.add_attention), datas);
        listview.setAdapter(adapter);
    }
}
