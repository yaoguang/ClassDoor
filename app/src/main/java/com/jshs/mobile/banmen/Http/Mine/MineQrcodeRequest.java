package com.jshs.mobile.banmen.Http.Mine;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.jshs.mobile.banmen.Http.CodeError;
import com.jshs.mobile.banmen.Http.Domain;
import com.jshs.mobile.banmen.Http.GetRequest;
import com.jshs.mobile.banmen.Http.StringResult;
import com.jshs.mobile.banmen.Tools.GsonUtils;

import java.io.UnsupportedEncodingException;

/**
 * Created by SZH on 2016/7/24.
 */
public class MineQrcodeRequest extends GetRequest<StringResult> {
    public MineQrcodeRequest(Response.Listener<StringResult> listener, Response.ErrorListener errorListener) {
        super(Domain.MINE_QRCODE, listener, errorListener);
    }

    @Override
    protected Response<StringResult> parseNetworkResponse(NetworkResponse response) {
        try {
            StringResult result = GsonUtils.getInstance().parse(StringResult.class, getResponseData(response));
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
}
