package com.jshs.mobile.banmen.BaseContent;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.jshs.mobile.banmen.Http.AsyncHttp;
import com.jshs.mobile.banmen.Models.DataBase;
import com.jshs.mobile.banmen.Tools.ScreenUtils;

import org.xutils.DbManager;
import org.xutils.x;

/**
 * Created by SZH on 2016/6/4.
 */
public class BaseApplication extends Application {
    private static BaseApplication mInstance;

    public static BaseApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        initNeedContext();

        DataBase.setContext(this);
        DataBase.getInstance(new DataBase.OnDbUpgradeListener() {
            @Override
            public void onDbUpgradeComplete(DbManager dbManager) {

            }
        });
    }

    private void initNeedContext() {
        AsyncHttp.setContext(this);
        ScreenUtils.setContext(this);
        x.Ext.init(this);
        x.Ext.setDebug(true);
        Fresco.initialize(this);
    }

    public String TAG() {
        return getClass().getSimpleName();
    }
}
