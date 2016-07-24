package com.jshs.mobile.banmen.FunctionPage.MinePager;

import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.jshs.mobile.banmen.BaseContent.XBaseActivity;
import com.jshs.mobile.banmen.Http.AsyncHttp;
import com.jshs.mobile.banmen.Http.CodeErrorListener;
import com.jshs.mobile.banmen.Http.Mine.MineDownloadRequest;
import com.jshs.mobile.banmen.Models.Mine.Download;
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
    @ViewInject(R.id.refreshlayout)
    private SwipeRefreshLayout refreshlayout;
    private ArrayList<Download> datas = new ArrayList<>();
    private MineDownloadAdapter adapter;

    @Override
    protected void initViews() {
        titleHolder = TitleHolder.initSimpleTitle(this, R.string.my_download);
    }

    public void initAction() {
        adapter = new MineDownloadAdapter(this, datas);
        listview.setAdapter(adapter);

        refreshlayout.setColorSchemeColors(R.color.title_back);
        refreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getMineDownload();
            }
        });
        refreshlayout.setRefreshing(true);
        getMineDownload();
    }

    private void getMineDownload() {
        AsyncHttp.getInstance().addRequest(new MineDownloadRequest(new Response.Listener<MineDownloadRequest.MineDownloadResult>() {
            @Override
            public void onResponse(MineDownloadRequest.MineDownloadResult response) {
                refreshlayout.setRefreshing(false);
                datas.clear();
                if (response.data != null)
                    datas.addAll(response.data);
                adapter.notifyDataSetChanged();
            }
        }, new CodeErrorListener(this) {
            @Override
            public void onErrorResponse(VolleyError error) {
                refreshlayout.setRefreshing(false);
                super.onErrorResponse(error);
            }
        }));
    }
}
