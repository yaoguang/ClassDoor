package com.jshs.mobile.banmen.BaseContent;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jshs.mobile.banmen.Tools.TitleHolder;

import org.xutils.x;

/**
 * Created by SZH on 2016/6/26.
 */
public class XBaseActivity extends BaseFragmentActivity {
    protected TitleHolder titleHolder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        init();
        initViews();
        initData();
        initAction();
    }

    protected void init() {
    }

    protected void initViews() {
    }

    protected void initData() {
    }

    protected void initAction() {
    }
}
