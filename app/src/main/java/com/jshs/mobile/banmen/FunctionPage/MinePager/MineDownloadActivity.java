package com.jshs.mobile.banmen.FunctionPage.MinePager;

import android.widget.ListView;

import com.jshs.mobile.banmen.BaseContent.XBaseActivity;
import com.jshs.mobile.banmen.R;
import com.jshs.mobile.banmen.Tools.TitleHolder;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

/**
 * Created by SZH on 2016/6/19.
 */
@ContentView(R.layout.simple_list_activity)
public class MineDownloadActivity extends XBaseActivity {
    @ViewInject(R.id.listview)
    private ListView listview;
    private ArrayList<String> datas = new ArrayList<>();
    private MineDownloadAdapter<String> adapter;

    @Override
    protected void initViews() {
        titleHolder = TitleHolder.initSimpleTitle(this, R.string.my_download);
    }

    public void initData() {
        datas.add("");
        datas.add("");
        datas.add("");
    }

    public void initAction() {
        adapter = new MineDownloadAdapter<>(this, datas);
        listview.setAdapter(adapter);
    }
}
