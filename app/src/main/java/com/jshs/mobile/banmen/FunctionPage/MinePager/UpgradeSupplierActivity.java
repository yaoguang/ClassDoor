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
 * Created by SZH on 2016/6/16.
 */
@ContentView(R.layout.mine_upgraded_supplier_activity)
public class UpgradeSupplierActivity extends BaseActivity {

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        assignViews();

        headIcon.setImageURI(Uri.parse("http://i2.hdslb.com/bfs/face/7c3fe391deb34e8b6f72794474ecad69c5c39494.jpg"));
    }
}
