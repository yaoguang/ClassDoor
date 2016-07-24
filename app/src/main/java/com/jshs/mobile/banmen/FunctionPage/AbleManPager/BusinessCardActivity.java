package com.jshs.mobile.banmen.FunctionPage.AbleManPager;

import android.net.Uri;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jshs.mobile.banmen.BaseContent.XBaseActivity;
import com.jshs.mobile.banmen.Http.AbleMan.GetAbleManInfo;
import com.jshs.mobile.banmen.Http.AsyncHttp;
import com.jshs.mobile.banmen.Http.CodeErrorListener;
import com.jshs.mobile.banmen.ModelUtils.UserUtils;
import com.jshs.mobile.banmen.Models.AbleMan.AbleMan;
import com.jshs.mobile.banmen.Models.User;
import com.jshs.mobile.banmen.R;
import com.jshs.mobile.banmen.Tools.TextTools;
import com.jshs.mobile.banmen.Tools.TitleHolder;

import org.xutils.view.annotation.ContentView;

/**
 * Created by SZH on 2016/6/25.
 */
@ContentView(R.layout.business_card_activity)
public class BusinessCardActivity extends XBaseActivity {
    private SimpleDraweeView icon;
    private TextView nickname;
    private TextView address;
    private TextView time;
    private TextView linkman;
    private TextView phone;

    private boolean isMe = false;
    private AbleMan ableMan;

    private void assignViews() {
        icon = (SimpleDraweeView) findViewById(R.id.icon);
        nickname = (TextView) findViewById(R.id.nickname);
        address = (TextView) findViewById(R.id.address);
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

    @Override
    protected void initData() {
        isMe = getIntent().getBooleanExtra("isMe", false);
        if (!this.isMe)
            ableMan = (AbleMan) getIntent().getSerializableExtra("ableMan");
        else {
            ableMan = new AbleMan();
            User user = UserUtils.getInstance().getUser();
            ableMan.id = user.getUid();
            ableMan.thumbnail = user.getThumbnail();
            ableMan.nickname = user.getNickname();
        }
    }

    @Override
    protected void initAction() {
        if (isMe)
            titleHolder.titleText.setText(R.string.mine_bussiness_card);

        refreshUI();
        getCardInfo();
    }

    private void refreshUI() {
        if (ableMan != null) {
            icon.setImageURI(Uri.parse(TextTools.getNotNull(ableMan.thumbnail)));
            nickname.setText(TextTools.getNotNull(ableMan.nickname));
            address.setText(TextTools.getNotNull(ableMan.address));
            time.setText("");
            linkman.setText(TextTools.getNotNull(ableMan.realname));
            phone.setText(TextTools.getNotNull(ableMan.moblie));
        } else {
            finish();
        }
    }

    private void getCardInfo() {
        if (ableMan == null)
            return;
        AsyncHttp.getInstance().addRequest(new GetAbleManInfo(ableMan.id,
                new Response.Listener<GetAbleManInfo.AbleManInfoReuslt>() {
                    @Override
                    public void onResponse(GetAbleManInfo.AbleManInfoReuslt response) {
                        ableMan = response.data;
                        refreshUI();
                    }
                },
                new CodeErrorListener(this) {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        super.onErrorResponse(error);
                    }
                }));
    }
}
