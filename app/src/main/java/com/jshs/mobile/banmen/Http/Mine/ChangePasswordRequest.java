package com.jshs.mobile.banmen.Http.Mine;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.jshs.mobile.banmen.Http.CodeError;
import com.jshs.mobile.banmen.Http.Domain;
import com.jshs.mobile.banmen.Http.PostRequest;
import com.jshs.mobile.banmen.Http.Result;
import com.jshs.mobile.banmen.Tools.GsonUtils;

import java.io.UnsupportedEncodingException;

/**
 * Created by SZH on 2016/7/24.
 */
public class ChangePasswordRequest extends PostRequest<Result> {
    public ChangePasswordRequest(String password, String repassword, Response.Listener<Result> listener, Response.ErrorListener errorListener) {
        super(Domain.CHANGE_PASSWORD, listener, errorListener);
        mRequestArgs.put("password", password);
        mRequestArgs.put("repassword", repassword);
    }

    @Override
    protected Response<Result> parseNetworkResponse(NetworkResponse response) {
        try {
            Result result = GsonUtils.getInstance().parse(Result.class, getResponseData(response));
            if (result != null && result.isSuccess()) {
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
}
