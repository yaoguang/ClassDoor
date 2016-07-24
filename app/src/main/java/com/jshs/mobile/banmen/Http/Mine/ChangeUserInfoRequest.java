package com.jshs.mobile.banmen.Http.Mine;

import android.text.TextUtils;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.jshs.mobile.banmen.Events.ChangeUserInfoEvent;
import com.jshs.mobile.banmen.Http.CodeError;
import com.jshs.mobile.banmen.Http.Domain;
import com.jshs.mobile.banmen.Http.PostRequest;
import com.jshs.mobile.banmen.Http.Result;
import com.jshs.mobile.banmen.ModelUtils.UserUtils;
import com.jshs.mobile.banmen.Models.User;
import com.jshs.mobile.banmen.Tools.GsonUtils;

import org.greenrobot.eventbus.EventBus;

import java.io.UnsupportedEncodingException;

/**
 * Created by SZH on 2016/7/24.
 */
public class ChangeUserInfoRequest extends PostRequest<Result> {
    /**
     * @param mobile
     * @param email
     * @param sex
     * @param birthday      Y-m-d H:i:S
     * @param listener
     * @param errorListener
     */

    public ChangeUserInfoRequest(String mobile, String email, String sex, String birthday, Response.Listener<Result> listener, Response.ErrorListener errorListener) {
        super(Domain.CHANGE_USERINFO, listener, errorListener);
        if (!TextUtils.isEmpty(mobile))
            mRequestArgs.put("mobile", mobile);
        if (!TextUtils.isEmpty(email))
            mRequestArgs.put("email", email);
        if (!TextUtils.isEmpty(sex))
            mRequestArgs.put("sex", sex);
        if (!TextUtils.isEmpty(birthday))
            mRequestArgs.put("birthday", birthday);
    }

    @Override
    protected Response<Result> parseNetworkResponse(NetworkResponse response) {
        try {
            Result result = GsonUtils.getInstance().parse(Result.class, getResponseData(response));
            if (result != null && result.isSuccess()) {
                onSuccess();
                return Response.success(result, HttpHeaderParser.parseCacheHeaders(response));
            } else {
                return Response.error(new CodeError(result.code));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.error(new CodeError());
    }

    private void onSuccess() {
        User user = UserUtils.getInstance().getUser();
        if (!TextUtils.isEmpty(mRequestArgs.get("mobile")))
            user.setMobile(mRequestArgs.get("mobile"));
        if (!TextUtils.isEmpty(mRequestArgs.get("email")))
            user.setEmail(mRequestArgs.get("email"));
        if (!TextUtils.isEmpty(mRequestArgs.get("sex")))
            user.setSex(mRequestArgs.get("sex"));
        if (!TextUtils.isEmpty(mRequestArgs.get("birthday")))
            user.setBirthday(mRequestArgs.get("birthday"));
        UserUtils.getInstance().updateUser(user);
        EventBus.getDefault().post(new ChangeUserInfoEvent());
    }
}
