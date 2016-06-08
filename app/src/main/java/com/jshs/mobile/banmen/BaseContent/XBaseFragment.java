package com.jshs.mobile.banmen.BaseContent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.xutils.x;

/**
 * Created by icezers on 16/6/8.
 */
public abstract class XBaseFragment extends BaseFragment {
    private View contentView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contentView = x.view().inject(this, inflater, container);
        return contentView;
    }

    public View getContent() {
        return contentView;
    }
}
