package com.jshs.mobile.banmen.Tools;

import android.text.TextUtils;

/**
 * Created by SZH on 2016/7/23.
 */
public class TextTools {
    public static String getNotNull(String str) {
        return TextUtils.isEmpty(str) ? "" : str;
    }
}
