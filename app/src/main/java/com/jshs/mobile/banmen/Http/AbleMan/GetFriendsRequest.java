package com.jshs.mobile.banmen.Http.AbleMan;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.jshs.mobile.banmen.Http.CodeError;
import com.jshs.mobile.banmen.Http.Domain;
import com.jshs.mobile.banmen.Http.GetRequest;
import com.jshs.mobile.banmen.Http.Result;
import com.jshs.mobile.banmen.Models.AbleMan.AbleMan;
import com.jshs.mobile.banmen.Tools.GsonUtils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by SZH on 2016/7/23.
 */
public class GetFriendsRequest extends GetRequest<GetFriendsRequest.GetFriendsResult> {

    public GetFriendsRequest(Response.Listener<GetFriendsResult> listener, Response.ErrorListener errorListener) {
        super(Domain.GET_FRIENDS_LIST, listener, errorListener);
    }

    @Override
    protected Response<GetFriendsResult> parseNetworkResponse(NetworkResponse response) {
        try {
            GetFriendsResult result = GsonUtils.getInstance().parse(GetFriendsResult.class, getResponseData(response));
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

    public class GetFriendsResult extends Result {
        public ArrayList<AbleMan> data;
    }
}
