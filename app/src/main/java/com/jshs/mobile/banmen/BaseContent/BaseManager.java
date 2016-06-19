package com.jshs.mobile.banmen.BaseContent;

import com.jshs.mobile.banmen.Models.DataBase;

/**
 * Created by Icezers on 2016/6/13.
 */
public class BaseManager {
    protected DataBase dataBase;

    public String TAG() {
        return getClass().getSimpleName();
    }

    public BaseManager() {
        dataBase = DataBase.getInstance();
    }
}
