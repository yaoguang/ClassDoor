package com.jshs.mobile.banmen.Http.Start;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.jshs.mobile.banmen.Http.CodeError;
import com.jshs.mobile.banmen.Http.Domain;
import com.jshs.mobile.banmen.Http.PostRequest;
import com.jshs.mobile.banmen.Http.StringResult;
import com.jshs.mobile.banmen.Tools.GsonUtils;

import java.io.UnsupportedEncodingException;

/**
 * Created by SZH on 2016/6/12.
 */
public class RegistRequest extends PostRequest<String> {
    public RegistRequest(String mobile, String password, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(Domain.REGIST, listener, errorListener);
        mRequestArgs.put("mobile", mobile);
        mRequestArgs.put("password", password);
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        try {
            StringResult result = GsonUtils.getInstance().parse(StringResult.class, getResponseData(response));
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
