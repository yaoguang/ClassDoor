package com.jshs.mobile.banmen.FunctionPage.GalleryPager;

import android.content.Intent;
import android.graphics.Rect;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.jshs.mobile.banmen.BaseContent.BaseHomePager;
import com.jshs.mobile.banmen.Http.AsyncHttp;
import com.jshs.mobile.banmen.Http.Gallery.GalleryRequest;
import com.jshs.mobile.banmen.Models.Gallery;
import com.jshs.mobile.banmen.R;
import com.jshs.mobile.banmen.Tools.TitleHolder;

import org.xutils.view.annotation.ContentView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by icezers on 16/6/8.
 */
@ContentView(R.layout.gallery_fragment)
public class GalleryFragment extends BaseHomePager implements SwipeRefreshLayout.OnRefreshListener, TitleHolder.TitleBtnClick {
    RecyclerView mRecyclerView;
    List<Gallery> mDatas;

    SimpleRecyclerCardAdapter mSimpleRecyclerAdapter;
    SwipeRefreshLayout refreshLayout;
    private int page;

    @Override
    public void onPagerSelect() {
        initDataAndView();
    }

    private void initDataAndView() {
        page = -1;
        mDatas = new ArrayList<>();
        reFreshData();
    }

    private void reFreshData() {
        page++;
        AsyncHttp.getInstance().addRequest(new GalleryRequest(page, new Response.Listener<List<Gallery>>() {
            @Override
            public void onResponse(List<Gallery> response) {
                if (response != null && response.size() != 0) {
                    mDatas.addAll(response);
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
        titleHolder = new TitleHolder(getActivity(), getContent(), R.string.gallery_title);
        titleHolder.initBtns(R.drawable.iconfont_search, R.drawable.iconfont_iconfontyiwen1, this);

        mRecyclerView = (RecyclerView) getContent().findViewById(R.id.recyclerView);

        mSimpleRecyclerAdapter = new SimpleRecyclerCardAdapter(getActivity());
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
                Intent intent=new Intent(getActivity(),GalleryDetailActivity.class);
                intent.putExtra("data",mDatas.get(pos));
                startActivity(intent);
            }
        });
        mSimpleRecyclerAdapter.setSwipeLastestPositionListener(new SimpleRecyclerCardAdapter.onSwipeLastestPositionListener() {
            @Override
            public void onSwipeLastestPosition() {
                reFreshData();
            }
        });
        refreshLayout = (SwipeRefreshLayout) getContent().findViewById(R.id.refreshlayout);
        refreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onLeftBtnClick(View view) {

    }

    @Override
    public void onRightBtnClick(View view) {

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
