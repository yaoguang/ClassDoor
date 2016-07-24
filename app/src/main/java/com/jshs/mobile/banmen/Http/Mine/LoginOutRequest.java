package com.jshs.mobile.banmen.Http.Mine;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.jshs.mobile.banmen.Http.CodeError;
import com.jshs.mobile.banmen.Http.Domain;
import com.jshs.mobile.banmen.Http.GetRequest;
import com.jshs.mobile.banmen.Http.Result;
import com.jshs.mobile.banmen.ModelUtils.UserUtils;
import com.jshs.mobile.banmen.Tools.GsonUtils;

import java.io.UnsupportedEncodingException;

/**
 * Created by SZH on 2016/7/24.
 */
public class LoginOutRequest extends GetRequest<Result> {
    public LoginOutRequest(Response.Listener<Result> listener, Response.ErrorListener errorListener) {
        super(Domain.LOGIN_OUT, listener, errorListener);
    }

    @Override
    protected Response<Result> parseNetworkResponse(NetworkResponse response) {
        try {
            Result result = GsonUtils.getInstance().parse(Result.class, getResponseData(response));
            if (result != null && result.isSuccess()) {

                UserUtils.getInstance().LoginOut();

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
