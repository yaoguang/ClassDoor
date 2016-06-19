package com.jshs.mobile.banmen.Models;

import com.jshs.mobile.banmen.BaseContent.BaseModel;

/**
 * Created by SZH on 2016/6/19.
 */
public class ServiceCategory extends BaseModel {
    /**
     * id : 1
     * name : 分类名称
     */

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
