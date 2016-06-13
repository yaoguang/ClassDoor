package com.jshs.mobile.banmen.Models;

import com.jshs.mobile.banmen.BaseContent.BaseModel;

/**
 * Created by SZH on 2016/6/12.
 */
public class UserModel extends BaseModel{

    /**
     * uid : 1
     * token : 32位编码
     * im_token : 融云token
     */

    private int uid;
    private String token;
    private String im_token;
    /**
     * id : 1
     * username : 用户名
     * nickname : 昵称
     * mobile : 手机号码
     * email : 电子邮箱
     * sex : 性别
     * birthday : 生日时间戳
     * signature : 签名
     * thumbnail : 头像缩略图URL
     * createtime : 账户创建时间戳
     */

    private int id;
    private String username;
    private String nickname;
    private String mobile;
    private String email;
    private String sex;
    private String birthday;
    private String signature;
    private String thumbnail;
    private String createtime;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getIm_token() {
        return im_token;
    }

    public void setIm_token(String im_token) {
        this.im_token = im_token;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
}
