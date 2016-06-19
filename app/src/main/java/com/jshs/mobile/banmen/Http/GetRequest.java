package com.jshs.mobile.banmen.Http;

import com.android.volley.Response;
import com.jshs.mobile.banmen.BaseContent.BaseRequest;
import com.jshs.mobile.banmen.ModelUtils.UserUtils;
import com.jshs.mobile.banmen.Models.User;

import java.util.HashMap;

/**
 * Created by Icezers on 2016/6/18.
 */
public abstract class GetRequest<T> extends BaseRequest<T> {

    public GetRequest(String url, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(Method.GET, url, errorListener);
        mListener = listener;
    }

    public static String formatUrl(String requestUri, String[] keys, String... valuses) {
        HashMap<String, String> requestArgs = null;
        if (valuses != null) {
            requestArgs = new HashMap<>();
            for (int i = 0; i < valuses.length; i++) {
                requestArgs.put(keys[i], valuses[i]);
            }
        }
        return AsyncHttp.formatUrl(requestUri, requestArgs);
    }

    public static String formatUserParams() {
        User user = UserUtils.getInstance().getUser();
        return "uid=" + user.getUid() + "&token=" + user.getToken();
    }

    public static String formatUserUrl(String requestUri) {
        User user = UserUtils.getInstance().getUser();
        return requestUri + "?uid=" + user.getUid() + "&token=" + user.getToken();
    }
}
