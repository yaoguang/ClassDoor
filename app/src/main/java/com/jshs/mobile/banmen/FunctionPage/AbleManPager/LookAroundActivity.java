package com.jshs.mobile.banmen.FunctionPage.AbleManPager;

import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.jshs.mobile.banmen.BaseContent.XBaseActivity;
import com.jshs.mobile.banmen.R;
import com.jshs.mobile.banmen.Tools.TitleHolder;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

/**
 * Created by SZH on 2016/6/25.
 */
@ContentView(R.layout.look_around_activity)
public class LookAroundActivity extends XBaseActivity {
    @ViewInject(R.id.listview)
    private ListView listView;
    private ArrayList<String> datas = new ArrayList<>();
    private BaseUserinfoAdapter<String> adapter;

    public void initViews() {
        titleHolder = new TitleHolder(this, R.string.look_around);
        titleHolder.initBtns(R.drawable.iconfont_addfill, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LookAroundActivity.this, "显示附近范围弹窗", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void initData() {
        datas.add("");
        datas.add("");
        datas.add("");
        datas.add("");
    }

    protected void initAction() {
        adapter = new BaseUserinfoAdapter<String>(this, getString(R.string.add), datas);
        listView.setAdapter(adapter);
    }
}
