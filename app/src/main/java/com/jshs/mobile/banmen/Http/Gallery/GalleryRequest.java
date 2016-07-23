package com.jshs.mobile.banmen.Http.Gallery;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.jshs.mobile.banmen.Http.CodeError;
import com.jshs.mobile.banmen.Http.Domain;
import com.jshs.mobile.banmen.Http.GetRequest;
import com.jshs.mobile.banmen.Http.Result;
import com.jshs.mobile.banmen.Http.Service.CategoryListResult;
import com.jshs.mobile.banmen.Models.Gallery;
import com.jshs.mobile.banmen.Tools.GsonUtils;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by Icezers on 2016/7/17.
 */
public class GalleryRequest extends GetRequest<List<Gallery>> {

    public GalleryRequest(int page,Response.Listener<List<Gallery>> listener, Response.ErrorListener errorListener) {
        super(formatUrl(Domain.GET_GALLERY_LIST, new String[]{"page"}, String.valueOf(page)), listener, errorListener);
    }

    @Override
    protected Response<List<Gallery>> parseNetworkResponse(NetworkResponse response) {
        try {
            GalleryResult result = GsonUtils.getInstance().parse(GalleryResult.class, getResponseData(response));
            if (result != null && result.isSuccess()) {
                return Response.success(result.data, HttpHeaderParser.parseCacheHeaders(response));
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

    class GalleryResult extends Result {
        public List<Gallery> data;
    }
}
