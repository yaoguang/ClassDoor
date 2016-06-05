package com.jshs.mobile.banmen.Http;


import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.jshs.mobile.banmen.Tools.MLog;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/12/31.
 * <p/>
 * Request的Post请求模板
 */
public abstract class PostRequest<T> extends Request<T> {
    private Listener<T> mListener;
    /**
     * 普通的请求参数
     * K-参数名，V-参数值
     */
    protected Map<String, String> mRequestArgs = new HashMap<>();

    /**
     * 使用自定义拼接方式后补充的请求参数
     */
    protected ArrayList<String> mParamsList;

    public PostRequest(String url, Listener<T> listener, ErrorListener errorListener) {
        super(Method.POST, Domain.ENDPOINT + url, errorListener);
        mListener = listener;

        addParams(mRequestArgs, mParamsList);
    }

    /**
     * 拼接参数
     */
    public void addParams(Map<String, String> mRequestArgs, ArrayList<String> mParamsList) {

    }

    @Override
    public Map<String, String> getParams() throws AuthFailureError {
        return mRequestArgs;
    }

    @Override
    protected abstract Response<T> parseNetworkResponse(NetworkResponse response);

    @Override
    protected VolleyError parseNetworkError(VolleyError volleyError) {
        return super.parseNetworkError(volleyError);
    }

    /**
     * 解析返回值为String
     */
    protected String getResponseData(NetworkResponse response) throws UnsupportedEncodingException {
        String data = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
        MLog.i("Request", data);
        return data;
    }

    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        if (mParamsList != null && !mParamsList.isEmpty()) {
            byte[] body = super.getBody();
            StringBuffer paramsBuffer = new StringBuffer(body == null ? "" : new String(body));
            for (String params : mParamsList) {
                paramsBuffer.append(params);
            }
            try {
                MLog.i("PostParams", paramsBuffer.toString());
                return paramsBuffer.toString().getBytes(getParamsEncoding());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return super.getBody();
    }
}
