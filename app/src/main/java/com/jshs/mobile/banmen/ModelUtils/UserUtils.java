package com.jshs.mobile.banmen.ModelUtils;

import com.jshs.mobile.banmen.BaseContent.BaseModelUtil;
import com.jshs.mobile.banmen.Models.DataBase;
import com.jshs.mobile.banmen.Models.User;

import org.apache.http.util.TextUtils;

/**
 * Created by Icezers on 2016/6/13.
 */
public class UserUtils extends BaseModelUtil {

    private static UserUtils _userUtils;

    private DataBase _dataBase;

    private User _user;

    private UserUtils() {
        _dataBase = DataBase.getInstance();
    }

    public static UserUtils getInstance() {
        if (_userUtils == null) {
            _userUtils = new UserUtils();
        }
        return _userUtils;
    }

    public User getUser() {
        if (_user == null) {
            _user = _dataBase.selectFrist(User.class);
        }
        if (_user == null) {
            _user = new User();
        }
        return _user;
    }

    public boolean isLogin() {
        return !TextUtils.isEmpty(getUser().getToken());
    }

    public void updateUser(User user) {
        _user = user;
        _dataBase.dropTable(User.class);
        _dataBase.saveOrUpdate(_user);
    }

    public void LoginOut() {
        _user = null;
        _dataBase.dropTable(User.class);
    }
}
