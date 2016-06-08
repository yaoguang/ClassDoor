package com.jshs.mobile.banmen.FunctionPage.StartPager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jshs.mobile.banmen.BaseContent.XBaseFragment;
import com.jshs.mobile.banmen.R;

import org.xutils.view.annotation.ContentView;

/**
 * Created by SZH on 2016/6/9.
 */
@ContentView(R.layout.regist_fragment)
public class RegistFragment extends XBaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return getContent();
    }
}
