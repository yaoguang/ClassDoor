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
public class SearchAbleManRequest extends GetRequest<SearchAbleManRequest.SearchAbleManResult> {
    private static String[] keys = {"pid", "job", "nickname", "sex", "adress", "page"};

    public SearchAbleManRequest(String pid, String job, String nickname, String sex, String adress, int page, Response.Listener<SearchAbleManResult> listener, Response.ErrorListener errorListener) {
        super(formatUrl(Domain.SERACH_ABLEMAN, keys, pid, job, nickname, sex, adress, String.valueOf(page)), listener, errorListener);
    }

    public SearchAbleManRequest(SearchCondition condition, int page, Response.Listener<SearchAbleManResult> listener, Response.ErrorListener errorListener) {
        super(formatUrl(Domain.SERACH_ABLEMAN, keys, condition.pid, condition.job, condition.nickname, condition.sex, condition.adress, String.valueOf(page)), listener, errorListener);
    }

    @Override
    protected Response<SearchAbleManResult> parseNetworkResponse(NetworkResponse response) {
        try {
            SearchAbleManResult result = GsonUtils.getInstance().parse(SearchAbleManResult.class, getResponseData(response));
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

    public class SearchAbleManResult extends Result {
        public ArrayList<AbleMan> data;
    }
}
