package com.jshs.mobile.banmen.FunctionPage.IMPager;

import android.view.View;

import com.jshs.mobile.banmen.BaseContent.BaseHomePager;
import com.jshs.mobile.banmen.R;
import com.jshs.mobile.banmen.Tools.TitleHolder;

import org.xutils.view.annotation.ContentView;

/**
 * Created by icezers on 16/6/8.
 */
@ContentView(R.layout.imlist_fragment)
public class IMFragment extends BaseHomePager {
    @Override
    public void onPagerSelect() {
    }

    @Override
    public void initViews() {
        titleHolder = new TitleHolder(getActivity(), getContent(), R.string.im);
        titleHolder.initBtns(R.drawable.iconfont_remind, R.drawable.iconfont_iconfontyiwenfill, new TitleHolder.TitleBtnClick() {
            @Override
            public void onLeftBtnClick(View view) {
            }

            @Override
            public void onRightBtnClick(View view) {
            }
        });
    }
}
