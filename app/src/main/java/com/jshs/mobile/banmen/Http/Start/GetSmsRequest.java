package com.jshs.mobile.banmen.Http.Start;

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
 * Created by SZH on 2016/6/10.
 */
public class GetSmsRequest extends PostRequest<String> {
    public GetSmsRequest(String mobile, SMSType type, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(Domain.GET_SMS_CODE, listener, errorListener);
        mRequestArgs.put("mobile", mobile);
        mRequestArgs.put("type", type.type);
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        try {
            TResult result = GsonUtils.getInstance().parse(TResult.class, getResponseData(response));
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

    private class TResult extends Result<String> {
    }

    public enum SMSType {
        REGIST("register"), CHANGE_PASSWORD("changepwd");
        private String type;

        private SMSType(String type) {
            this.type = type;
        }
    }
}
