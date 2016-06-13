package com.jshs.mobile.banmen.Models;

import com.jshs.mobile.banmen.BaseContent.BaseModel;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by SZH on 2016/6/12.
 */
@Table(name = "user")
public class User extends BaseModel {

    @Column(name = "uuid", autoGen = true, isId = true)
    private int uuid;

    /**
     * uid : 1
     * token : 32位编码
     * im_token : 融云token
     */
    @Column(name = "uid")
    private int uid;

    @Column(name = "token")
    private String token;

    @Column(name = "im_token")
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

    @Column(name = "id")
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "email")
    private String email;

    @Column(name = "sex")
    private String sex;

    @Column(name = "birthday")
    private String birthday;

    @Column(name = "signature")
    private String signature;

    @Column(name = "thumbnail")
    private String thumbnail;

    @Column(name = "createtime")
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

    public int getUuid() {
        return uuid;
    }

    public void setUuid(int uuid) {
        this.uuid = uuid;
    }
}
