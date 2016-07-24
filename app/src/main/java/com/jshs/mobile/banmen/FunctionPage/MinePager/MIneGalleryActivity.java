package com.jshs.mobile.banmen.FunctionPage.MinePager;

import android.content.Intent;
import android.graphics.Rect;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.jshs.mobile.banmen.BaseContent.XBaseActivity;
import com.jshs.mobile.banmen.FunctionPage.GalleryPager.GalleryDetailActivity;
import com.jshs.mobile.banmen.Http.AsyncHttp;
import com.jshs.mobile.banmen.Http.Mine.GalleryResult;
import com.jshs.mobile.banmen.Http.Mine.MineGalleryRequest;
import com.jshs.mobile.banmen.Models.Gallery;
import com.jshs.mobile.banmen.R;
import com.jshs.mobile.banmen.Tools.TitleHolder;

import org.xutils.view.annotation.ContentView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SZH on 2016/7/24.
 */
@ContentView(R.layout.gallery_fragment)
public class MIneGalleryActivity extends XBaseActivity implements SwipeRefreshLayout.OnRefreshListener {
    RecyclerView mRecyclerView;
    List<Gallery> mDatas;

    SimpleRecyclerCardAdapter mSimpleRecyclerAdapter;
    SwipeRefreshLayout refreshLayout;
    private int page;

    @Override
    protected void initData() {
        initDataAndView();
    }

    private void initDataAndView() {
        page = -1;
        mDatas = new ArrayList<>();
        reFreshData();
    }

    private void reFreshData() {
        page++;
        AsyncHttp.getInstance().addRequest(new MineGalleryRequest(page,
                new Response.Listener<GalleryResult>() {
                    @Override
                    public void onResponse(GalleryResult response) {
                        if (response.data != null && response.data.size() != 0) {
                            mDatas.addAll(response.data);
                        } else {
                            String tmp[] = {
                                    "http://s15.sinaimg.cn/middle/654f2148gc033779d570e&690",
                                    "http://s11.sinaimg.cn/middle/654f2148gc03379b4d81a&690",
                                    "http://www.cnnb.com.cn/pic/0/02/26/69/2266974_737679.jpg",
                                    "http://www.cnnb.com.cn/pic/0/02/26/69/2266977_825574.jpg",
                                    "http://www.cnnb.com.cn/pic/0/02/26/69/2266978_028776.jpg"
                            };
                            for (int i = 0; i < tmp.length; i++) {
                                Gallery gallery = new Gallery();
                                gallery.setContent(tmp[i]);
                                mDatas.add(gallery);
                            }
                        }
                        mSimpleRecyclerAdapter.setDatas(mDatas);
                        onRefreshComplete();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String tmp[] = {
                        "http://s15.sinaimg.cn/middle/654f2148gc033779d570e&690",
                        "http://s11.sinaimg.cn/middle/654f2148gc03379b4d81a&690",
                        "http://www.cnnb.com.cn/pic/0/02/26/69/2266974_737679.jpg",
                        "http://www.cnnb.com.cn/pic/0/02/26/69/2266977_825574.jpg",
                        "http://www.cnnb.com.cn/pic/0/02/26/69/2266978_028776.jpg"
                };
                for (int i = 0; i < tmp.length; i++) {
                    Gallery gallery = new Gallery();
                    gallery.setContent(tmp[i]);
                    mDatas.add(gallery);
                }
                mSimpleRecyclerAdapter.setDatas(mDatas);
                onRefreshComplete();
            }
        }));
    }

    @Override
    public void onRefresh() {
        initDataAndView();
    }

    private void onRefreshComplete() {
        refreshLayout.setRefreshing(false);
        mSimpleRecyclerAdapter.setIsLoading(false);
    }

    @Override
    public void initViews() {
        titleHolder = TitleHolder.initSimpleTitle(this, R.string.mine_gallery);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        mSimpleRecyclerAdapter = new SimpleRecyclerCardAdapter(this);
        mRecyclerView.setAdapter(mSimpleRecyclerAdapter);
        //设置网格布局管理器
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(15));
        mSimpleRecyclerAdapter.setOnItemActionListener(new SimpleRecyclerCardAdapter.OnItemActionListener() {

            @Override
            public boolean onItemLongClickListener(View v, int pos) {
                return false;
            }

            @Override
            public void onItemClickListener(View v, int pos) {
                Intent intent = new Intent(MIneGalleryActivity.this, GalleryDetailActivity.class);
                intent.putExtra("data", mDatas.get(pos));
                startActivity(intent);
            }
        });
        mSimpleRecyclerAdapter.setSwipeLastestPositionListener(new SimpleRecyclerCardAdapter.onSwipeLastestPositionListener() {
            @Override
            public void onSwipeLastestPosition() {
                reFreshData();
            }
        });
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.refreshlayout);
        refreshLayout.setOnRefreshListener(this);
    }

    public class SpacesItemDecoration extends RecyclerView.ItemDecoration {

        private int space;

        public SpacesItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.left = space;
            outRect.right = space;
            outRect.bottom = space;
            //注释这两行是为了上下间距相同
//			if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = space;
//			}
        }
    }

}
