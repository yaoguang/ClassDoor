package com.jshs.mobile.banmen.FunctionPage.MinePager;

import android.view.View;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.jshs.mobile.banmen.BaseContent.XBaseActivity;
import com.jshs.mobile.banmen.Events.LoginOutEvent;
import com.jshs.mobile.banmen.Http.AsyncHttp;
import com.jshs.mobile.banmen.Http.CodeErrorListener;
import com.jshs.mobile.banmen.Http.Mine.LoginOutRequest;
import com.jshs.mobile.banmen.Http.Result;
import com.jshs.mobile.banmen.R;
import com.jshs.mobile.banmen.Tools.TitleHolder;

import org.greenrobot.eventbus.EventBus;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;

/**
 * Created by SZH on 2016/6/19.
 */
@ContentView(R.layout.set_activity)
public class SetActivity extends XBaseActivity {

    @Override
    protected void initViews() {
        titleHolder = TitleHolder.initSimpleTitle(this, R.string.set);
    }

    @Event(value = {R.id.user_info, R.id.password, R.id.phone, R.id.clear_cache, R.id.about, R.id.notescontact, R.id.login_out})
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_info:
                break;
            case R.id.password:
                break;
            case R.id.phone:
                break;
            case R.id.clear_cache:
                break;
            case R.id.about:
                break;
            case R.id.notescontact:
                break;
            case R.id.login_out:
                loginOut();
                break;
        }
    }

    private void loginOut() {
        AsyncHttp.getInstance().addRequest(new LoginOutRequest(new Response.Listener<Result>() {
            @Override
            public void onResponse(Result response) {
                EventBus.getDefault().post(new LoginOutEvent());
                finish();
            }
        }, new CodeErrorListener(this) {
            @Override
            public void onErrorResponse(VolleyError error) {
                super.onErrorResponse(error);
            }
        }));
    }
}
