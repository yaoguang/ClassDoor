package com.jshs.mobile.banmen.BaseContent;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.jshs.mobile.banmen.Http.AsyncHttp;
import com.jshs.mobile.banmen.Models.DataBase;
import com.jshs.mobile.banmen.Tools.ScreenUtils;

import org.xutils.DbManager;
import org.xutils.x;

import cn.smssdk.SMSSDK;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

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
        SMSSDK.initSDK(this, "145a09821cc00", "8ccaef7aa2b4b7e5553ff241030b6c99");
        Fresco.initialize(this);
        DataBase.setContext(this);
        DataBase.getInstance(new DataBase.OnDbUpgradeListener() {
            @Override
            public void onDbUpgradeComplete(DbManager dbManager) {

            }
        });


        /**
         * OnCreate 会被多个进程重入，这段保护代码，确保只有您需要使用 RongIM 的进程和 Push 进程执行了 init。
         * io.rong.push 为融云 push 进程名称，不可修改。
         */
        if (getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext())) ||
                "io.rong.push".equals(getCurProcessName(getApplicationContext()))) {
            /**
             * IMKit SDK调用第一步 初始化
             */
            RongIM.init(this,"pvxdm17jxjrfr");
        }
    }

    private void initNeedContext() {
        AsyncHttp.setContext(this);
        ScreenUtils.setContext(this);
        x.Ext.init(this);
        x.Ext.setDebug(true);
        Fresco.initialize(this);
    }

    /**
     * 获得当前进程的名字
     *
     * @param context
     * @return 进程号
     */
    public static String getCurProcessName(Context context) {

        int pid = android.os.Process.myPid();

        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);

        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
                .getRunningAppProcesses()) {

            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

    /**
     * 建立与融云服务器的连接
     *
     * @param token
     */
    public void connect(String token) {
        if (getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext()))) {
            /**
             * IMKit SDK调用第二步,建立与服务器的连接
             */
            RongIM.connect("0nvVlbpwaJt2dK7gUc9LJUQ0qCR6mqjC7cyt1S2h1mgwoLxCAzyajdReLtDoYfs46RPXJjsQ1GEFoWdCILV8Zw==", new RongIMClient.ConnectCallback() {
                /**
                 * Token 错误，在线上环境下主要是因为 Token 已经过期，您需要向 App Server 重新请求一个新的 Token
                 */
                @Override
                public void onTokenIncorrect() {
                    Log.d("LoginActivity", "--onTokenIncorrect");
                }

                /**
                 * 连接融云成功
                 * @param userid 当前 token
                 */
                @Override
                public void onSuccess(String userid) {
                    Log.d("LoginActivity", "--onSuccess" + userid);
                }

                /**
                 * 连接融云失败
                 * @param errorCode 错误码，可到官网 查看错误码对应的注释
                 */
                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {
                    Log.d("LoginActivity", "--onError" + errorCode);
                }
            });
        }
    }

    public String TAG() {
        return getClass().getSimpleName();
    }
}
