package com.jshs.mobile.banmen.Http;

import com.android.volley.VolleyError;

/**
 * Created by Administrator on 2016/1/4.
 * <p>
 * 包含请求返回错误码信息的VolleyError
 */
public class CodeError extends VolleyError {
    private int resultCode;

    private String codeConstant;

    public CodeError() {
        codeConstant = ResultCodeConstants.getCodeMessage(-1);
    }

    public CodeError(int resultCode) {
        this.resultCode = resultCode;
        codeConstant = ResultCodeConstants.getCodeMessage(resultCode);
    }

    public CodeError(int resultCode, String codeConstant) {
        this.resultCode = resultCode;
        this.codeConstant = codeConstant;
    }

    public int getResultCode() {
        return resultCode;
    }

    public String getCodeConstant() {
        return codeConstant;
    }
}
