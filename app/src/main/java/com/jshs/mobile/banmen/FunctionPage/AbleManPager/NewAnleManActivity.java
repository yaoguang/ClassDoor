package com.jshs.mobile.banmen.FunctionPage.AbleManPager;

import android.view.View;
import android.widget.ListView;

import com.jshs.mobile.banmen.BaseContent.XBaseActivity;
import com.jshs.mobile.banmen.R;
import com.jshs.mobile.banmen.Tools.TitleHolder;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

/**
 * Created by SZH on 2016/6/26.
 */
@ContentView(R.layout.new_ableman_activity)
public class NewAnleManActivity extends XBaseActivity {
    @ViewInject(R.id.listview)
    private ListView listView;
    private ArrayList<String> datas = new ArrayList<>();
    private BaseUserinfoAdapter<String> adapter;


    @Override
    protected void initViews() {
        titleHolder = new TitleHolder(this, R.string.new_ableman);
        titleHolder.initBtns(R.drawable.icon_personadd, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
    }

    public void initData() {
        datas.add("");
        datas.add("");
        datas.add("");
        datas.add("");
    }

    public void initAction() {
        adapter = new BaseUserinfoAdapter<String>(this, getString(R.string.add), datas);
        listView.setAdapter(adapter);
    }
}
