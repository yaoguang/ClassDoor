package com.jshs.mobile.banmen.BaseContent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jshs.mobile.banmen.Tools.TitleHolder;

import org.xutils.x;

/**
 * Created by icezers on 16/6/8.
 */
public abstract class XBaseFragment extends BaseFragment {
    protected TitleHolder titleHolder;
    private View contentView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contentView = x.view().inject(this, inflater, container);
        init();
        return contentView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void init() {
        initViews();
        initData();
        initAction();
    }

    public void initViews() {
    }

    public void initData() {
    }

    public void initAction() {
    }

    public View getContent() {
        return contentView;
    }

    public <T> T mFindViewById(int id) {
        return (T) getContent().findViewById(id);
    }
}
