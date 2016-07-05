package com.jshs.mobile.banmen.FunctionPage.GalleryPager;

import android.graphics.Rect;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.jshs.mobile.banmen.BaseContent.BaseHomePager;
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
    List<String> mDatas;

    SimpleRecyclerCardAdapter mSimpleRecyclerAdapter;
    SwipeRefreshLayout refreshLayout;

    @Override
    public void onPagerSelect() {
        initDataAndView();
    }

    private void initDataAndView() {
        mDatas = new ArrayList<>();
        mDatas.add("http://s15.sinaimg.cn/middle/654f2148gc033779d570e&690");
        mDatas.add("http://s11.sinaimg.cn/middle/654f2148gc03379b4d81a&690");
        mDatas.add("http://www.cnnb.com.cn/pic/0/02/26/69/2266974_737679.jpg");
        mDatas.add("http://www.cnnb.com.cn/pic/0/02/26/69/2266977_825574.jpg");
        mDatas.add("http://www.cnnb.com.cn/pic/0/02/26/69/2266978_028776.jpg");
        mDatas.add("http://s15.sinaimg.cn/middle/654f2148gc033779d570e&690");
        mDatas.add("http://s11.sinaimg.cn/middle/654f2148gc03379b4d81a&690");
        mDatas.add("http://www.cnnb.com.cn/pic/0/02/26/69/2266974_737679.jpg");
        mDatas.add("http://www.cnnb.com.cn/pic/0/02/26/69/2266977_825574.jpg");
        mDatas.add("http://www.cnnb.com.cn/pic/0/02/26/69/2266978_028776.jpg");
        mDatas.add("http://s15.sinaimg.cn/middle/654f2148gc033779d570e&690");
        mDatas.add("http://s11.sinaimg.cn/middle/654f2148gc03379b4d81a&690");
        mDatas.add("http://www.cnnb.com.cn/pic/0/02/26/69/2266974_737679.jpg");
        mDatas.add("http://www.cnnb.com.cn/pic/0/02/26/69/2266977_825574.jpg");
        mDatas.add("http://www.cnnb.com.cn/pic/0/02/26/69/2266978_028776.jpg");
        mDatas.add("http://s15.sinaimg.cn/middle/654f2148gc033779d570e&690");
        mDatas.add("http://s11.sinaimg.cn/middle/654f2148gc03379b4d81a&690");
        mDatas.add("http://www.cnnb.com.cn/pic/0/02/26/69/2266974_737679.jpg");
        mDatas.add("http://www.cnnb.com.cn/pic/0/02/26/69/2266977_825574.jpg");
        mDatas.add("http://www.cnnb.com.cn/pic/0/02/26/69/2266978_028776.jpg");
        mDatas.add("http://s15.sinaimg.cn/middle/654f2148gc033779d570e&690");
        mDatas.add("http://s11.sinaimg.cn/middle/654f2148gc03379b4d81a&690");
        mDatas.add("http://www.cnnb.com.cn/pic/0/02/26/69/2266974_737679.jpg");
        mDatas.add("http://www.cnnb.com.cn/pic/0/02/26/69/2266977_825574.jpg");
        mDatas.add("http://www.cnnb.com.cn/pic/0/02/26/69/2266978_028776.jpg");
        mDatas.add("http://s15.sinaimg.cn/middle/654f2148gc033779d570e&690");
        mDatas.add("http://s11.sinaimg.cn/middle/654f2148gc03379b4d81a&690");
        mDatas.add("http://www.cnnb.com.cn/pic/0/02/26/69/2266974_737679.jpg");
        mDatas.add("http://www.cnnb.com.cn/pic/0/02/26/69/2266977_825574.jpg");
        mDatas.add("http://www.cnnb.com.cn/pic/0/02/26/69/2266978_028776.jpg");
        mDatas.add("http://s15.sinaimg.cn/middle/654f2148gc033779d570e&690");
        mDatas.add("http://s11.sinaimg.cn/middle/654f2148gc03379b4d81a&690");
        mDatas.add("http://www.cnnb.com.cn/pic/0/02/26/69/2266974_737679.jpg");
        mDatas.add("http://www.cnnb.com.cn/pic/0/02/26/69/2266977_825574.jpg");
        mDatas.add("http://www.cnnb.com.cn/pic/0/02/26/69/2266978_028776.jpg");
        mDatas.add("http://s15.sinaimg.cn/middle/654f2148gc033779d570e&690");
        mDatas.add("http://s11.sinaimg.cn/middle/654f2148gc03379b4d81a&690");
        mDatas.add("http://www.cnnb.com.cn/pic/0/02/26/69/2266974_737679.jpg");
        mDatas.add("http://www.cnnb.com.cn/pic/0/02/26/69/2266977_825574.jpg");
        mDatas.add("http://www.cnnb.com.cn/pic/0/02/26/69/2266978_028776.jpg");
        mDatas.add("http://s15.sinaimg.cn/middle/654f2148gc033779d570e&690");
        mDatas.add("http://s11.sinaimg.cn/middle/654f2148gc03379b4d81a&690");
        mDatas.add("http://www.cnnb.com.cn/pic/0/02/26/69/2266974_737679.jpg");
        mDatas.add("http://www.cnnb.com.cn/pic/0/02/26/69/2266977_825574.jpg");
        mDatas.add("http://www.cnnb.com.cn/pic/0/02/26/69/2266978_028776.jpg");
        mDatas.add("http://s15.sinaimg.cn/middle/654f2148gc033779d570e&690");
        mDatas.add("http://s11.sinaimg.cn/middle/654f2148gc03379b4d81a&690");
        mDatas.add("http://www.cnnb.com.cn/pic/0/02/26/69/2266974_737679.jpg");
        mDatas.add("http://www.cnnb.com.cn/pic/0/02/26/69/2266977_825574.jpg");
        mDatas.add("http://www.cnnb.com.cn/pic/0/02/26/69/2266978_028776.jpg");
        mDatas.add("http://s15.sinaimg.cn/middle/654f2148gc033779d570e&690");
        mDatas.add("http://s11.sinaimg.cn/middle/654f2148gc03379b4d81a&690");
        mDatas.add("http://www.cnnb.com.cn/pic/0/02/26/69/2266974_737679.jpg");
        mDatas.add("http://www.cnnb.com.cn/pic/0/02/26/69/2266977_825574.jpg");
        mDatas.add("http://www.cnnb.com.cn/pic/0/02/26/69/2266978_028776.jpg");
        mDatas.add("http://s15.sinaimg.cn/middle/654f2148gc033779d570e&690");

        Collections.shuffle(mDatas);

        mSimpleRecyclerAdapter.setDatas(mDatas);

    }

    @Override
    public void onRefresh() {
        initDataAndView();
        refreshLayout.setRefreshing(false);
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
                Toast.makeText(getActivity(), "-长按-" + pos, Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public void onItemClickListener(View v, int pos) {
                Toast.makeText(getActivity(), "-单击-" + pos, Toast.LENGTH_SHORT).show();
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
