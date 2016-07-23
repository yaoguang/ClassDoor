package com.jshs.mobile.banmen.Models;

/**
 * Created by SZH on 2016/7/11.
 */
public class GalleryEntry {

    /**
     * id : 2
     * uid : 发布人ID
     * name : 图片名
     * description : 描述
     * type : 图片类型
     * address : 所在地
     * content : 图片路径
     * createtime : 入住时间
     */

    private int id;
    private String uid;
    private String name;
    private String description;
    private String type;
    private String address;
    private String content;
    private String createtime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
}
