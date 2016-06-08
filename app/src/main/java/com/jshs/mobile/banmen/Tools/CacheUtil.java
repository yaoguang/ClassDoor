package com.jshs.mobile.banmen.Tools;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by icezers on 15/11/2.
 */
public class CacheUtil {

    Context mContext;
    SharedPreferences mPreferences;

    public CacheUtil(Context context, String fileKey) {
        mContext = context;
        mPreferences = mContext.getSharedPreferences(fileKey, Context.MODE_MULTI_PROCESS);
    }

    public void saveCache(String valueKey, String value) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(valueKey, value);
        editor.commit();
    }

    public void saveCache(String valueKey, Integer value) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt(valueKey, value);
        editor.commit();
    }

    public void saveCache(String valueKey, Long value) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putLong(valueKey, value);
        editor.commit();
    }

    public void saveCache(String valueKey, Float value) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putFloat(valueKey, value);
        editor.commit();
    }

    public void saveCache(String valueKey, boolean value) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putBoolean(valueKey, value);
        editor.commit();
    }

    public String readStringCache(String valueKey) {
        return mPreferences.getString(valueKey, "");
    }

    public Integer readIntCache(String valueKey) {
        return mPreferences.getInt(valueKey, 0);
    }

    public Float readFloatCache(String valueKey) {
        return mPreferences.getFloat(valueKey, 0);
    }

    public Long readLongCache(String valueKey) {
        return mPreferences.getLong(valueKey, 0);
    }

    public boolean readBoobleCache(String valueKey) {
        return mPreferences.getBoolean(valueKey, false);
    }


    public void removeCache(String valueKey) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.remove(valueKey);
        editor.commit();
    }

    public void clearCache() {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.clear();
        editor.commit();
    }

}
