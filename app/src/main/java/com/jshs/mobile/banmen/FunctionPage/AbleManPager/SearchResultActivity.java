package com.jshs.mobile.banmen.FunctionPage.AbleManPager;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.jshs.mobile.banmen.BaseContent.XBaseActivity;
import com.jshs.mobile.banmen.Http.AbleMan.AddFriendRequest;
import com.jshs.mobile.banmen.Http.AbleMan.SearchAbleManRequest;
import com.jshs.mobile.banmen.Http.AbleMan.SearchCondition;
import com.jshs.mobile.banmen.Http.AsyncHttp;
import com.jshs.mobile.banmen.Http.CodeErrorListener;
import com.jshs.mobile.banmen.Http.Result;
import com.jshs.mobile.banmen.Models.AbleMan.AbleMan;
import com.jshs.mobile.banmen.R;
import com.jshs.mobile.banmen.Tools.TitleHolder;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

/**
 * Created by SZH on 2016/6/26.
 */
@ContentView(R.layout.search_result_activity)
public class SearchResultActivity extends XBaseActivity {
    @ViewInject(R.id.listview)
    private ListView listView;
    @ViewInject(R.id.refreshlayout)
    private SwipeRefreshLayout refreshLayout;
    private ArrayList<AbleMan> datas = new ArrayList<>();
    private BaseUserinfoAdapter<AbleMan> adapter;

    private SearchCondition condition;

    private int page = 0;

    @Override
    protected void initViews() {
        titleHolder = TitleHolder.initSimpleTitle(this, R.string.search_result);
    }

    @Override
    protected void initData() {
        condition = (SearchCondition) getIntent().getSerializableExtra("info");
    }

    public void initAction() {
        adapter = new BaseUserinfoAdapter(this, getString(R.string.add)) {
            @Override
            protected void initHolderViews(AbleMan data, ViewHolder holder, int position) {
                super.initHolderViews(data, holder, position);
                holder.action.setVisibility(data.relationType == 2 ? View.GONE : View.VISIBLE);
            }

            @Override
            protected void onItemClick(View convertView, AbleMan data, ViewHolder holder, int position) {
                super.onItemClick(convertView, data, holder, position);
                switch (convertView.getId()) {
                    case R.id.action:
                        addFriend(data.id);
                        break;
                    default:
                        break;
                }
            }
        };
        adapter.setBottomListener(new BaseUserinfoAdapter.OnAdapterBottomListener() {
            @Override
            public void onAdapterBottom() {
                requestInfo(page);
            }
        });
        listView.setAdapter(adapter);

        refreshLayout.setColorSchemeColors(R.color.title_back);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 0;
                requestInfo(page);
            }
        });

        requestInfo(page);
    }

    private void addFriend(int id) {
        AsyncHttp.getInstance().addRequest(new AddFriendRequest(id, new Response.Listener<Result>() {
            @Override
            public void onResponse(Result response) {

            }
        }, new CodeErrorListener(this) {
            @Override
            public void onErrorResponse(VolleyError error) {
                super.onErrorResponse(error);
            }
        }));
    }

    private void requestInfo(final int page) {
        AsyncHttp.getInstance().addRequest(new SearchAbleManRequest(condition, page, new Response.Listener<SearchAbleManRequest.SearchAbleManResult>() {
            @Override
            public void onResponse(SearchAbleManRequest.SearchAbleManResult response) {
                SearchResultActivity.this.page += 1;
                if (page == 0)
                    datas.clear();
                if (response.data != null)
                    datas.addAll(response.data);
                adapter.setDatas(datas);
                requestRefreshOver();
            }
        }, new CodeErrorListener(this) {
            @Override
            public void onErrorResponse(VolleyError error) {
                requestRefreshOver();
                super.onErrorResponse(error);
            }
        }));
    }

    private void requestRefreshOver() {
        refreshLayout.setRefreshing(false);
        adapter.loadingOver();
    }
}
