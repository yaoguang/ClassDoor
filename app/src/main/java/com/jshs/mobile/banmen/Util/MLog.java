package com.jshs.mobile.banmen.Util;

import android.util.Log;

/**
 * Created by Administrator on 2016/1/6.
 * 控制Log日志显示模式
 */
public class MLog {
    public static boolean isDebug = true;

    public static void isDebug(boolean debug) {
        isDebug = debug;
    }

    public static void i(String tag, String msg) {
        if (isDebug) {
            Log.i(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (isDebug) {
            Log.e(tag, msg);
        }
    }
}
