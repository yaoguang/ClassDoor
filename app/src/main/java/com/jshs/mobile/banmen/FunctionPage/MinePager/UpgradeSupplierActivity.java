package com.jshs.mobile.banmen.FunctionPage.MinePager;

import android.net.Uri;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jshs.mobile.banmen.BaseContent.XBaseActivity;
import com.jshs.mobile.banmen.R;
import com.jshs.mobile.banmen.Tools.TitleHolder;

import org.xutils.view.annotation.ContentView;

/**
 * Created by SZH on 2016/6/16.
 */
@ContentView(R.layout.mine_upgraded_supplier_activity)
public class UpgradeSupplierActivity extends XBaseActivity {
    private SimpleDraweeView headIcon;
    private TextView name;
    private EditText address;
    private TextView time;
    private EditText linkman;
    private EditText phone;
    private TextView serviceAreaText;
    private EditText serviceArea;
    private TextView submit;

    private void assignViews() {
        headIcon = (SimpleDraweeView) findViewById(R.id.head_icon);
        name = (TextView) findViewById(R.id.name);
        address = (EditText) findViewById(R.id.address);
        time = (TextView) findViewById(R.id.time);
        linkman = (EditText) findViewById(R.id.linkman);
        phone = (EditText) findViewById(R.id.phone);
        serviceAreaText = (TextView) findViewById(R.id.service_area_text);
        serviceArea = (EditText) findViewById(R.id.service_area);
        submit = (TextView) findViewById(R.id.submit);
    }

    @Override
    protected void initViews() {
        assignViews();
        titleHolder = new TitleHolder(this, R.string.upgrade_supplier);
        titleHolder.initBtns(R.drawable.icon_compile, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        headIcon.setImageURI(Uri.parse("http://i2.hdslb.com/bfs/face/7c3fe391deb34e8b6f72794474ecad69c5c39494.jpg"));
    }
}
