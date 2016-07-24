package com.jshs.mobile.banmen.FunctionPage.MinePager;

import android.net.Uri;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jshs.mobile.banmen.BaseContent.XBaseActivity;
import com.jshs.mobile.banmen.Http.AsyncHttp;
import com.jshs.mobile.banmen.Http.CodeErrorListener;
import com.jshs.mobile.banmen.Http.Mine.MineQrcodeRequest;
import com.jshs.mobile.banmen.Http.StringResult;
import com.jshs.mobile.banmen.R;
import com.jshs.mobile.banmen.Tools.TextTools;
import com.jshs.mobile.banmen.Tools.TitleHolder;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by SZH on 2016/7/24.
 */
@ContentView(R.layout.mine_qrcode_activity)
public class MineScanActivity extends XBaseActivity {
    @ViewInject(R.id.qrcode)
    SimpleDraweeView qricon;

    @Override
    protected void initViews() {
        titleHolder = TitleHolder.initSimpleTitle(this, R.string.my_qrcode);
    }

    @Override
    protected void initAction() {
        qricon.setImageURI(Uri.parse("http://h.hiphotos.baidu.com/image/pic/item/3bf33a87e950352a5936aa0a5543fbf2b2118b59.jpg"));
        getQrcode();
    }

    private void getQrcode() {
        AsyncHttp.getInstance().addRequest(new MineQrcodeRequest(new Response.Listener<StringResult>() {
            @Override
            public void onResponse(StringResult response) {
                qricon.setImageURI(Uri.parse(TextTools.getNotNull(response.data)));
            }
        }, new CodeErrorListener(this) {
            @Override
            public void onErrorResponse(VolleyError error) {
                super.onErrorResponse(error);
            }
        }));
    }
}
