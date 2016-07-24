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

/**
 * Created by SZH on 2016/7/23.
 */
public class GetAbleManInfo extends GetRequest<GetAbleManInfo.AbleManInfoReuslt> {
    public GetAbleManInfo(int pid, Response.Listener<AbleManInfoReuslt> listener, Response.ErrorListener errorListener) {
        super(formatUrl(Domain.GET_ABLEMAN_INFO, new String[]{"pid"}, String.valueOf(pid)), listener, errorListener);
    }

    @Override
    protected Response<AbleManInfoReuslt> parseNetworkResponse(NetworkResponse response) {
        try {
            AbleManInfoReuslt result = GsonUtils.getInstance().parse(AbleManInfoReuslt.class, getResponseData(response));
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

    public class AbleManInfoReuslt extends Result {
        public AbleMan data;
    }
}
