package com.jshs.mobile.banmen.FunctionPage.AbleManPager;

import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jshs.mobile.banmen.BaseContent.XBaseActivity;
import com.jshs.mobile.banmen.Http.AbleMan.AddFriendRequest;
import com.jshs.mobile.banmen.Http.AbleMan.GetAbleManInfo;
import com.jshs.mobile.banmen.Http.AsyncHttp;
import com.jshs.mobile.banmen.Http.CodeErrorListener;
import com.jshs.mobile.banmen.Http.Result;
import com.jshs.mobile.banmen.Models.AbleMan.AbleMan;
import com.jshs.mobile.banmen.R;
import com.jshs.mobile.banmen.Tools.TextTools;
import com.jshs.mobile.banmen.Tools.TitleHolder;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;

/**
 * Created by SZH on 2016/6/26.
 */
@ContentView(R.layout.net_user_fei_activity)
public class NetUserFeiActivity extends XBaseActivity {
    private SimpleDraweeView icon;
    private TextView nickname;
    private TextView name;
    private TextView time;
    private TextView linkman;
    private TextView phone;
    private TextView addBySameFriend;
    private LinearLayout sameFriendLay;
    private LinearLayout sameFriendIconLay;
    private ImageView rightTag;
    private TextView friend;
    private TextView speak;

    private void assignViews() {
        icon = (SimpleDraweeView) findViewById(R.id.icon);
        nickname = (TextView) findViewById(R.id.nickname);
        name = (TextView) findViewById(R.id.name);
        time = (TextView) findViewById(R.id.time);
        linkman = (TextView) findViewById(R.id.linkman);
        phone = (TextView) findViewById(R.id.phone);
        addBySameFriend = (TextView) findViewById(R.id.add_by_same_friend);
        sameFriendLay = (LinearLayout) findViewById(R.id.same_friend_lay);
        sameFriendIconLay = (LinearLayout) findViewById(R.id.same_friend_icon_lay);
        rightTag = (ImageView) findViewById(R.id.right_tag);
        friend = (TextView) findViewById(R.id.friend);
        speak = (TextView) findViewById(R.id.speak);
    }

    private AbleMan ableMan;

    @Override
    protected void initViews() {
        titleHolder = new TitleHolder(this, R.string.net_user_fei);
        titleHolder.initBtns(R.drawable.iconfont_plus, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NetUserFeiActivity.this, "显示菜单弹窗", Toast.LENGTH_SHORT).show();
            }
        });

        assignViews();
    }

    @Override
    protected void initData() {
        ableMan = (AbleMan) getIntent().getSerializableExtra("ableMan");
    }

    @Override
    protected void initAction() {
        refreshUI();
        getAbleManInfo();
    }

    @Event(value = {R.id.add_by_same_friend, R.id.same_friend_lay, R.id.friend, R.id.speak})
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_by_same_friend:
                Toast.makeText(this, "前往陌生人关系链界面", Toast.LENGTH_SHORT).show();
                break;
            case R.id.same_friend_lay:

                break;
            case R.id.friend:
                if (ableMan != null)
                    friendAbleMan();
                break;
            case R.id.speak:
                break;
        }
    }

    private void friendAbleMan() {
        if (ableMan.relationType == 2) {

        } else {
            addFirend();
        }
    }

    private void addFirend() {
        AsyncHttp.getInstance().addRequest(new AddFriendRequest(ableMan.id, new Response.Listener<Result>() {
            @Override
            public void onResponse(Result response) {

            }
        }, new CodeErrorListener(this) {
            @Override
            public void onErrorResponse(VolleyError error) {
                super.onErrorResponse(error);
            }
        }));
    }

    public void getAbleManInfo() {
        if (ableMan != null)
            AsyncHttp.getInstance().addRequest(new GetAbleManInfo(ableMan.id, new Response.Listener<GetAbleManInfo.AbleManInfoReuslt>() {
                @Override
                public void onResponse(GetAbleManInfo.AbleManInfoReuslt response) {
                    ableMan = response.data;
                    refreshUI();
                }
            }, new CodeErrorListener(this) {
                @Override
                public void onErrorResponse(VolleyError error) {
                    super.onErrorResponse(error);
                }
            }));
    }

    private void refreshUI() {
        if (ableMan != null) {
            if (ableMan.relationType == 2) {
                addBySameFriend.setVisibility(View.GONE);
                sameFriendLay.setVisibility(View.VISIBLE);
                getSameFriend();

                friend.setText(R.string.cancel_friend);
            } else {
                sameFriendLay.setVisibility(View.GONE);
                addBySameFriend.setVisibility(View.VISIBLE);

                friend.setText(R.string.add_friend);
            }
            icon.setImageURI(Uri.parse(TextTools.getNotNull(ableMan.thumbnail)));
            nickname.setText(TextTools.getNotNull(ableMan.nickname));
            name.setText(TextTools.getNotNull(ableMan.address));
            time.setText("2016-null");
            linkman.setText(TextTools.getNotNull(ableMan.realname));
            phone.setText(TextTools.getNotNull(ableMan.moblie));
        }
    }

    private void getSameFriend() {
        Toast.makeText(this, "获取相同的好友", Toast.LENGTH_SHORT).show();
    }
}