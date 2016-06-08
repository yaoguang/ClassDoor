package com.jshs.mobile.banmen.BaseContent;

import android.app.Application;

import com.jshs.mobile.banmen.Http.AsyncHttp;
import com.jshs.mobile.banmen.Tools.ScreenUtils;

import org.xutils.x;

/**
 * Created by SZH on 2016/6/4.
 */
public class BaseApplication extends Application {
    private BaseApplication mInstance;

    public BaseApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        initNeedContext();
    }

    private void initNeedContext() {
        AsyncHttp.setContext(this);
        ScreenUtils.setContext(this);
        x.Ext.init(this);
        x.Ext.setDebug(true);
    }

    public String TAG() {
        return getClass().getSimpleName();
    }
}
