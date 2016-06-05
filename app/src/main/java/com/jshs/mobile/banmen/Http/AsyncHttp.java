package com.jshs.mobile.banmen.Http;

import android.content.Context;
import android.content.res.AssetManager;
import android.net.ConnectivityManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * 网络请求管理类
 */
public final class AsyncHttp {
    private static final String HTTP_USERAGNET = "Android";
    private static final String HTTP_DISKCACHE = "volley";
    private static final String RES_ASSETS = "ASSETS:";
    private static final String RES_SDCARD = "SDCARD:";
    private static final String RES_HTTP = "HTTP:";
    private static AsyncHttp msInstance;
    private static Context msContext;
    //Volley对象
    private RequestQueue mRequestQueue;

    //单例模式
    private AsyncHttp(Context tContext) {
        final AssetManager mAssetManager = tContext.getAssets();
        mRequestQueue = Volley.newRequestQueue(msContext);

        connectivity = (ConnectivityManager) tContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        mRequestQueue.start();
    }

    public static byte[] toBytes(InputStream inputStream) {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[10000];
        int count = -1;
        try {
            while ((count = inputStream.read(data, 0, 10000)) != -1)
                outStream.write(data, 0, count);
            data = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outStream.toByteArray();
    }

    public static synchronized void setContext(Context tContext) {
        if (msInstance != null) {
            throw new IllegalStateException("Context class was " + msContext
                    + " but is trying to be reset to " + tContext);
        }
        msContext = tContext;
        msInstance = new AsyncHttp(msContext);
    }

    public static synchronized AsyncHttp getInstance() {
        if (msInstance == null) {
            throw new IllegalStateException(
                    "Call setContext(Context) to create AsyncHttp instance first!!!");
        }
        return msInstance;
    }

    public static String formatUrl(String requestUri,
                                   Map<String, String> requestArgs) {
        StringBuilder stringBuilder = new StringBuilder();
        if (requestArgs != null && !requestArgs.isEmpty()) {
            for (Map.Entry<String, String> entry : requestArgs.entrySet()) {
                stringBuilder.append(stringBuilder.length() == 0 ? "?" : "&");
                stringBuilder.append(entry.getKey()).append("=")
                        .append(entry.getValue());
            }
        }
        return stringBuilder.insert(0, requestUri).toString();
    }

    public static String formatUrl(String requestUrl, String requestUri,
                                   Map<String, String> requestArgs) {
        StringBuilder stringBuilder = new StringBuilder();
        if (requestArgs != null && !requestArgs.isEmpty()) {
            for (Map.Entry<String, String> entry : requestArgs.entrySet()) {
                stringBuilder.append(stringBuilder.length() == 0 ? "?" : "&");
                stringBuilder.append(entry.getKey()).append("=")
                        .append(entry.getValue());
            }
        }
        return stringBuilder.insert(0, requestUri).insert(0, requestUrl)
                .toString();
    }

    /**
     * 取消所有请求
     *
     * @param requestFilter
     */
    public void cancelAll(RequestQueue.RequestFilter requestFilter) {
        mRequestQueue.cancelAll(requestFilter);
    }

    /**
     * 取消指定tag的请求
     *
     * @param tag
     */
    public void cancelAll(final Object tag) {
        mRequestQueue.cancelAll(tag);
    }

    /**
     * 发起请求
     *
     * @param request
     * @return
     */
    public Request<?> addRequest(Request<?> request) {
        return mRequestQueue.add(request);
    }

    /**
     * 发起指定标签的请求
     *
     * @param tag
     * @param request
     * @return
     */
    public Request<?> addRequest(final Object tag, Request<?> request) {
        request.setTag(tag);
        return addRequest(request);
    }

    /**
     * 是否有含指定tag的请求正在执行
     *
     * @param tag
     * @return
     */
    public boolean hasRequest(Object tag) {
        return mRequestQueue.hasReuqest(tag);
    }

    private ConnectivityManager connectivity;


}
