package com.jshs.mobile.banmen.FunctionPage.AbleManPager;

import android.graphics.Color;
import android.view.View;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.jshs.mobile.banmen.BaseContent.XBaseActivity;
import com.jshs.mobile.banmen.Http.AbleMan.AddFriendRequest;
import com.jshs.mobile.banmen.Http.AbleMan.ConfirmAddFriendRequest;
import com.jshs.mobile.banmen.Http.AbleMan.NewAbleManRequest;
import com.jshs.mobile.banmen.Http.AsyncHttp;
import com.jshs.mobile.banmen.Http.CodeErrorListener;
import com.jshs.mobile.banmen.Http.Result;
import com.jshs.mobile.banmen.ModelUtils.UserUtils;
import com.jshs.mobile.banmen.Models.AbleMan.NewAbleMan;
import com.jshs.mobile.banmen.R;
import com.jshs.mobile.banmen.Tools.TitleHolder;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

/**
 * Created by SZH on 2016/6/26.
 */
@ContentView(R.layout.new_ableman_activity)
public class NewAnleManActivity extends XBaseActivity {
    @ViewInject(R.id.listview)
    private ListView listView;
    private ArrayList<NewAbleMan> datas = new ArrayList<>();
    private BaseUserinfoAdapter<NewAbleMan> adapter;

    @Override
    protected void initViews() {
        titleHolder = new TitleHolder(this, R.string.new_ableman);
        titleHolder.initBtns(R.drawable.icon_personadd, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void initAction() {
        adapter = new BaseUserinfoAdapter<NewAbleMan>(this, getString(R.string.add), datas) {
            @Override
            protected void initHolderViews(NewAbleMan data, ViewHolder holder, int position) {
                super.initHolderViews(data, holder, position);
                int uid = UserUtils.getInstance().getUser().getUid();
                if (data.priuid == uid) {
                    holder.action.setText(R.string.has_send);
                    holder.action.setBackgroundColor(Color.WHITE);
                } else if (data.subuid == uid) {
                    holder.action.setText(R.string.accept);
                    holder.action.setBackgroundResource(R.drawable.soild_arc_red_3dp);
                } else if (data.relationType == 2) {
                    holder.action.setText(R.string.has_agree);
                    holder.action.setBackgroundColor(Color.WHITE);
                } else {
                    holder.action.setText(R.string.add);
                    holder.action.setBackgroundResource(R.drawable.frame_arc_cuttingline_3dp);
                }
            }

            @Override
            protected void onItemClick(View convertView, NewAbleMan data, ViewHolder holder, int position) {
                super.onItemClick(convertView, data, holder, position);
                switch (convertView.getId()) {
                    case R.id.action:
                        int uid = UserUtils.getInstance().getUser().getUid();
                        if (data.subuid == uid) {
                            confirmAddFriend(data.id);
                        } else if (data.priuid != uid && data.relationType != 2) {
                            addFirend(data.id);
                        }
                        break;
                    default:
                        break;
                }
            }
        };
        listView.setAdapter(adapter);

        getNewAbleMan();
    }

    private void getNewAbleMan() {
        AsyncHttp.getInstance().addRequest(new NewAbleManRequest(new Response.Listener<NewAbleManRequest.NewAbleManResult>() {
            @Override
            public void onResponse(NewAbleManRequest.NewAbleManResult response) {

            }
        }, new CodeErrorListener(this) {
            @Override
            public void onErrorResponse(VolleyError error) {
                super.onErrorResponse(error);
            }
        }));
    }

    private void confirmAddFriend(int pid) {
        AsyncHttp.getInstance().addRequest(new ConfirmAddFriendRequest(pid, new Response.Listener<Result>() {
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

    private void addFirend(int pid) {
        AsyncHttp.getInstance().addRequest(new AddFriendRequest(pid, new Response.Listener<Result>() {
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
}
