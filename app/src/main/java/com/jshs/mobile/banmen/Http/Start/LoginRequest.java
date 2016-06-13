package com.jshs.mobile.banmen.Http.Start;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.jshs.mobile.banmen.Http.CodeError;
import com.jshs.mobile.banmen.Http.Domain;
import com.jshs.mobile.banmen.Http.PostRequest;
import com.jshs.mobile.banmen.Models.User;
import com.jshs.mobile.banmen.Tools.GsonUtils;

import java.io.UnsupportedEncodingException;

/**
 * Created by SZH on 2016/6/12.
 */
public class LoginRequest extends PostRequest<User> {
    public LoginRequest(String username, String password, Response.Listener<User> listener, Response.ErrorListener errorListener) {
        super(Domain.LOGIN, listener, errorListener);
        mRequestArgs.put("username", username);
        mRequestArgs.put("password", password);
    }

    @Override
    protected Response<User> parseNetworkResponse(NetworkResponse response) {
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
