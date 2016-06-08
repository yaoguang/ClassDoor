package com.jshs.mobile.banmen.FunctionPage.MinePager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jshs.mobile.banmen.BaseContent.BaseHomePager;
import com.jshs.mobile.banmen.R;

/**
 * Created by icezers on 16/6/8.
 */
public class MineFragment extends BaseHomePager {
    View _rootView;

    @Override
    public void onPagerSelect() {
//        TextView textView = (TextView) _rootView.findViewById(R.id.text);
//        textView.setText(TAG());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        _rootView = inflater.inflate(R.layout.my_fragment, container, false);
        return _rootView;
    }
}
