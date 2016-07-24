package com.jshs.mobile.banmen.Http.Mine;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.jshs.mobile.banmen.Http.CodeError;
import com.jshs.mobile.banmen.Http.Domain;
import com.jshs.mobile.banmen.Http.GetRequest;
import com.jshs.mobile.banmen.Http.Result;
import com.jshs.mobile.banmen.Models.Mine.Download;
import com.jshs.mobile.banmen.Tools.GsonUtils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by SZH on 2016/7/24.
 */
public class MineDownloadRequest extends GetRequest<MineDownloadRequest.MineDownloadResult> {
    public MineDownloadRequest(Response.Listener<MineDownloadResult> listener, Response.ErrorListener errorListener) {
        super(Domain.SERACH_ABLEMAN, listener, errorListener);
    }

    @Override
    protected Response<MineDownloadResult> parseNetworkResponse(NetworkResponse response) {
        try {
            MineDownloadResult result = GsonUtils.getInstance().parse(MineDownloadResult.class, getResponseData(response));
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

    public class MineDownloadResult extends Result {
        public ArrayList<Download> data;
    }
}
