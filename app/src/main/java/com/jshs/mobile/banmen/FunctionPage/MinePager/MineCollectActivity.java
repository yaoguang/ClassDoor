package com.jshs.mobile.banmen.FunctionPage.MinePager;

import android.os.Bundle;
import android.widget.ListView;

import com.jshs.mobile.banmen.BaseContent.BaseActivity;
import com.jshs.mobile.banmen.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

/**
 * Created by SZH on 2016/6/19.
 */
@ContentView(R.layout.simple_list_activity)
public class MineCollectActivity extends BaseActivity {
    @ViewInject(R.id.listview)
    private ListView listview;
    private ArrayList<String> datas = new ArrayList<>();
    private MineCollectAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);

        initData();
        initAction();
    }

    private void initData() {
        datas.add("");
        datas.add("");
        datas.add("");
    }

    private void initAction() {
        adapter = new MineCollectAdapter<>(this, datas);
        listview.setAdapter(adapter);
    }
}
