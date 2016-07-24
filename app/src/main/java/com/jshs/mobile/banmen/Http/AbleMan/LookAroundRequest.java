package com.jshs.mobile.banmen.Http.AbleMan;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.jshs.mobile.banmen.Http.CodeError;
import com.jshs.mobile.banmen.Http.Domain;
import com.jshs.mobile.banmen.Http.GetRequest;
import com.jshs.mobile.banmen.Http.Result;
import com.jshs.mobile.banmen.Models.AbleMan.AroundAbleMan;
import com.jshs.mobile.banmen.Tools.GsonUtils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by SZH on 2016/7/23.
 */
public class LookAroundRequest extends GetRequest<LookAroundRequest.LookAroundResult> {
    public static String[] keys = {"lng", "lat", "distance"};

    public LookAroundRequest(String lng, String lat, String distance, Response.Listener<LookAroundResult> listener, Response.ErrorListener errorListener) {
        super(formatUrl(Domain.GET_AROUND_ABLEMAN, keys, lng, lat, distance), listener, errorListener);
    }

    @Override
    protected Response<LookAroundResult> parseNetworkResponse(NetworkResponse response) {
        try {
            LookAroundResult result = GsonUtils.getInstance().parse(LookAroundResult.class, getResponseData(response));
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

    public class LookAroundResult extends Result {
        public ArrayList<AroundAbleMan> data;
    }
}
