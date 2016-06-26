package com.jshs.mobile.banmen.FunctionPage.AbleManPager;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.jshs.mobile.banmen.BaseContent.BaseHomePager;
import com.jshs.mobile.banmen.R;
import com.jshs.mobile.banmen.Tools.TitleHolder;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;

/**
 * Created by icezers on 16/6/8.
 */
@ContentView(R.layout.ableman_fragment)
public class AbleManFragment extends BaseHomePager implements TitleHolder.TitleBtnClick {
    @Override
    public void onPagerSelect() {
    }

    @Override
    public void initViews() {
        titleHolder = new TitleHolder(getActivity(), getContent(), R.string.able_man);
        titleHolder.initBtns(R.drawable.iconfont_search, R.drawable.iconfont_iconfontyiwen1, this);
    }

    @Event(value = {R.id.item_1, R.id.item_2, R.id.item_3})
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.item_1:
                startActivity(new Intent(getActivity(), MineCardcaseActivity.class));
                break;
            case R.id.item_2:
                startActivity(new Intent(getActivity(), LookAroundActivity.class));
                break;
            case R.id.item_3:
                startActivity(new Intent(getActivity(), NewAnleManActivity.class));
                break;
        }
    }

    @Override
    public void onLeftBtnClick(View view) {
        startActivity(new Intent(getActivity(), SearchAbleManActivity.class));
    }

    @Override
    public void onRightBtnClick(View view) {
        Toast.makeText(getActivity(), "暂无内容", Toast.LENGTH_SHORT).show();
    }
}
