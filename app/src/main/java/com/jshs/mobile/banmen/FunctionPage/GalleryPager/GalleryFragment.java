package com.jshs.mobile.banmen.FunctionPage.GalleryPager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jshs.mobile.banmen.BaseContent.BaseFragment;
import com.jshs.mobile.banmen.BaseContent.BaseHomePager;
import com.jshs.mobile.banmen.R;

/**
 * Created by icezers on 16/6/8.
 */
public class GalleryFragment extends BaseHomePager {
    View _rootView;

    @Override
    public void onPagerSelect() {
        TextView textView = (TextView) _rootView.findViewById(R.id.text);
        textView.setText(TAG());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        _rootView = inflater.inflate(R.layout.demo_fragment, container, false);
        return _rootView;
    }
}
