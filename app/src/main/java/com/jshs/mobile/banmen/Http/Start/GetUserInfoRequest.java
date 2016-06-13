package com.jshs.mobile.banmen.Http.Start;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.jshs.mobile.banmen.Http.CodeError;
import com.jshs.mobile.banmen.Http.Domain;
import com.jshs.mobile.banmen.Http.PostRequest;
import com.jshs.mobile.banmen.Models.UserModel;
import com.jshs.mobile.banmen.Tools.GsonUtils;

import java.io.UnsupportedEncodingException;

/**
 * Created by SZH on 2016/6/12.
 */
public class GetUserInfoRequest extends PostRequest<UserModel> {
    public GetUserInfoRequest(int uid, String token, Response.Listener<UserModel> listener, Response.ErrorListener errorListener) {
        super(Domain.GET_USER_INFO, listener, errorListener);
        mRequestArgs.put("uid", String.valueOf(uid));
        mRequestArgs.put("token", token);
    }

    @Override
    protected Response<UserModel> parseNetworkResponse(NetworkResponse response) {
        try {
            UserResult result = GsonUtils.getInstance().parse(UserResult.class, getResponseData(response));
            if (result != null && result.isSuccess()) {
                return Response.success(result.data, HttpHeaderParser.parseCacheHeaders(response));
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
}
