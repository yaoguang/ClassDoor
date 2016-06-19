package com.jshs.mobile.banmen.Http.Service;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.jshs.mobile.banmen.Http.CodeError;
import com.jshs.mobile.banmen.Http.Domain;
import com.jshs.mobile.banmen.Http.GetRequest;
import com.jshs.mobile.banmen.Models.ServiceCategory;
import com.jshs.mobile.banmen.Tools.GsonUtils;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by SZH on 2016/6/19.
 */
public class GetCategoryListRequest extends GetRequest<List<ServiceCategory>> {
    public GetCategoryListRequest(int pid, Response.Listener<List<ServiceCategory>> listener, Response.ErrorListener errorListener) {
        super(formatUrl(Domain.GET_CATEGORY_LIST, new String[]{"pid"}, String.valueOf(pid)), listener, errorListener);
    }

    @Override
    protected Response<List<ServiceCategory>> parseNetworkResponse(NetworkResponse response) {
        try {
            CategoryListResult result = GsonUtils.getInstance().parse(CategoryListResult.class, getResponseData(response));
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
}
