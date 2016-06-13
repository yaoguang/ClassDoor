package com.jshs.mobile.banmen.BaseContent;

import com.jshs.mobile.banmen.Models.DataBase;

/**
 * Created by Icezers on 2016/6/13.
 */
public class BasaManager {
    DataBase dataBase;

    public String TAG() {
        return getClass().getSimpleName();
    }

    public BasaManager() {
        dataBase = DataBase.getInstance();
    }



}
