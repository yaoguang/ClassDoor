package com.jshs.mobile.banmen.Models.AbleMan;

import java.io.Serializable;

/**
 * Created by SZH on 2016/7/23.
 */
public class AbleMan implements Comparable, Serializable {
    public int id;
    public String nickname;
    public String moblie;
    public String signature;
    public String realname;
    public String address;
    public String thumbnail;
    public int relationType;

    @Override
    public int compareTo(Object another) {
        if (another instanceof AbleMan) {
            AbleMan ableMan = (AbleMan) another;

        }
        return 0;
    }
}
