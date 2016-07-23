package com.jshs.mobile.banmen.Http.Mine;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.jshs.mobile.banmen.Http.CodeError;
import com.jshs.mobile.banmen.Http.Domain;
import com.jshs.mobile.banmen.Http.GetRequest;
import com.jshs.mobile.banmen.ModelUtils.UserUtils;
import com.jshs.mobile.banmen.Tools.GsonUtils;

import java.io.UnsupportedEncodingException;

/**
 * Created by SZH on 2016/7/11.
 */
public class MineGalleryRequest extends GetRequest<GalleryResult> {
    private static String key[] = {"uid", "page"};

    public MineGalleryRequest(int page, Response.Listener<GalleryResult> listener, Response.ErrorListener errorListener) {
        super(formatUrl(Domain.GET_MINE_GALLERY, key, String.valueOf(UserUtils.getInstance().getUser().getUid()), String.valueOf(page)), listener, errorListener);
    }

    @Override
    protected Response<GalleryResult> parseNetworkResponse(NetworkResponse response) {
        try {
            GalleryResult result = GsonUtils.getInstance().parse(GalleryResult.class, getResponseData(response));
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
