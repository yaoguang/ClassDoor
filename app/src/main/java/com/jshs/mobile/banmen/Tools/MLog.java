package com.jshs.mobile.banmen.Tools;

import android.text.TextUtils;
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

	private static void i(String tag, String msg) {
		if (isDebug) {
			Log.i(tag, msg);
		}
	}

	private static void e(String tag, String msg) {
		if (isDebug) {
			Log.e(tag, msg);
		}
	}

	private static void d(String tag, String msg) {
		if (isDebug) {
			Log.d(tag, msg);
		}
	}

	public static void print(String tag, MLOG_LEVEL level, String msg) {
		if (TextUtils.isEmpty(tag)) tag = "MLog";
		switch (level) {
			case I:
				i(tag, msg);
				break;
			case D:
				d(tag, msg);
				break;
			case E:
				e(tag, msg);
				break;
		}
	}

	public static void print(MLOG_LEVEL level, String msg) {
		print(null, level, msg);
	}
}
