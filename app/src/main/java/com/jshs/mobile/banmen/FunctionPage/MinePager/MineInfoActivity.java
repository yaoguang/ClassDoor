package com.jshs.mobile.banmen.FunctionPage.MinePager;

import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jshs.mobile.banmen.BaseContent.BaseActivity;
import com.jshs.mobile.banmen.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.x;

/**
 * Created by SZH on 2016/6/19.
 */
@ContentView(R.layout.mine_info_activity)
public class MineInfoActivity extends BaseActivity {
    private SimpleDraweeView headIcon;
    private TextView nickname;
    private EditText name;
    private TextView sexMan;
    private TextView sexWoman;
    private TextView sexPrivary;
    private TextView birthday;
    private EditText personalizedSignature;
    private EditText phone;

    private void assignViews() {
        headIcon = (SimpleDraweeView) findViewById(R.id.head_icon);
        nickname = (TextView) findViewById(R.id.nickname);
        name = (EditText) findViewById(R.id.name);
        sexMan = (TextView) findViewById(R.id.sex_man);
        sexWoman = (TextView) findViewById(R.id.sex_woman);
        sexPrivary = (TextView) findViewById(R.id.sex_privary);
        birthday = (TextView) findViewById(R.id.birthday);
        personalizedSignature = (EditText) findViewById(R.id.personalized_signature);
        phone = (EditText) findViewById(R.id.phone);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);

        assignViews();

        headIcon.setImageURI(Uri.parse("http://i2.hdslb.com/bfs/face/7c3fe391deb34e8b6f72794474ecad69c5c39494.jpg"));
    }
}
