package com.jshs.mobile.banmen.FunctionPage.AbleManPager;

import android.net.Uri;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jshs.mobile.banmen.BaseContent.XBaseActivity;
import com.jshs.mobile.banmen.R;
import com.jshs.mobile.banmen.Tools.TitleHolder;

import org.xutils.view.annotation.ContentView;

/**
 * Created by SZH on 2016/6/25.
 */
@ContentView(R.layout.business_card_activity)
public class BusinessCardActivity extends XBaseActivity {
    private SimpleDraweeView icon;
    private TextView nickname;
    private TextView name;
    private TextView time;
    private TextView linkman;
    private TextView phone;

    private void assignViews() {
        icon = (SimpleDraweeView) findViewById(R.id.icon);
        nickname = (TextView) findViewById(R.id.nickname);
        name = (TextView) findViewById(R.id.name);
        time = (TextView) findViewById(R.id.time);
        linkman = (TextView) findViewById(R.id.linkman);
        phone = (TextView) findViewById(R.id.phone);
    }

    @Override
    protected void initViews() {
        assignViews();
        icon.setImageURI(Uri.parse("http://i2.hdslb.com/bfs/face/7c3fe391deb34e8b6f72794474ecad69c5c39494.jpg"));

        titleHolder = TitleHolder.initSimpleTitle(this, R.string.friend_card);
    }
}
