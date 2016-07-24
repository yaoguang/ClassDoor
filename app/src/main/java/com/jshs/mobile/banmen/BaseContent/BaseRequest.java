package com.jshs.mobile.banmen.BaseContent;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.jshs.mobile.banmen.Http.Domain;
import com.jshs.mobile.banmen.ModelUtils.UserUtils;
import com.jshs.mobile.banmen.Tools.MLOG_LEVEL;
import com.jshs.mobile.banmen.Tools.MLog;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Icezers on 2016/6/18.
 */
public abstract class BaseRequest<T> extends Request<T> {
    private Map<String, String> mHeadParams;
    protected Response.Listener<T> mListener;

    public BaseRequest(int method, String url, Response.ErrorListener listener) {
        super(method, Domain.ENDPOINT + url, listener);
        setUserHeadParams();
    }

    protected abstract Response<T> parseNetworkResponse(NetworkResponse response);

    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }

    /**
     * 解析返回值为String
     */
    protected String getResponseData(NetworkResponse response) throws UnsupportedEncodingException {
        String data = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
        MLog.print(TAG() + " Request", MLOG_LEVEL.D, data);
        return data;
    }

    protected void setUserHeadParams() {
        if (UserUtils.getInstance().isLogin()) {
            Map<String, String> userParams = new HashMap<>();
            userParams.put("uid", String.valueOf(UserUtils.getInstance().getUser().getUid()));
            userParams.put("token", UserUtils.getInstance().getUser().getToken());
        }
    }

    protected void addHeadParams(Map<String, String> headParams) {
        if (mHeadParams == null)
            mHeadParams = new HashMap<>();
        mHeadParams.putAll(headParams);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        if (mHeadParams == null)
            return super.getHeaders();
        else return mHeadParams;
    }

    public String TAG() {
        return getClass().getSimpleName();
    }
}
