package com.jshs.mobile.banmen.Http;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.jshs.mobile.banmen.BaseContent.BaseApplication;
import com.jshs.mobile.banmen.Tools.MLOG_LEVEL;
import com.jshs.mobile.banmen.Tools.MLog;

/**
 * Created by Administrator on 2016/1/4.
 * <p>
 * 处理错误信息的ErrorListener
 */
public class CodeErrorListener implements Response.ErrorListener {
    private Context mContext;
    // 是否弹窗显示信息
    private boolean isShowCodeConstant = true;
    // 是否在符合条件时执行跳转到登录界面
    private boolean comeLogin = true;

    public CodeErrorListener() {
        mContext = BaseApplication.getInstance();
    }

    public CodeErrorListener(Context context) {
        mContext = context;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        try {
            // 优先处理包含错误码的Error
            if (error instanceof CodeError) {
                int errorCode = ((CodeError) error).getResultCode();
                // 特殊错误码处理
                if (onCodeError(errorCode))
                    return;
                // 未登录错误处理
//                if (errorCode == ResultCodeConstants.UN_LOGIN && comeLogin) {
//                    Toast.makeText(mContext, ((CodeError) error).getCodeConstant(), Toast.LENGTH_SHORT).show();
//
//                    Intent intent = new Intent(mContext, LoginActivity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    mContext.startActivity(intent);
//                } else if (isShowCodeConstant) {
//                    // 普通错误码处理方式
//                    AlertServer.show(((CodeError) error).getCodeConstant());
//                }
            } else if (isShowCodeConstant) {
                // 不包含错误码的特殊Error处理方式
//                if (error instanceof NullNetWorkError) {
//                    AlertServer.show(R.string.error_null_network);
//                } else if (error instanceof NetworkError) {
//                    AlertServer.show(mContext.getString(R.string.error_error_network) + "(" + error.networkResponse.statusCode + ")");
//                } else if (error instanceof ServerError) {
//                    AlertServer.show(R.string.error_null_service);
//                } else if (error instanceof TimeoutError) {
//                    AlertServer.show(R.string.error_time_out);
//                }
            }
        } catch (Exception e) {
            MLog.print("Request", MLOG_LEVEL.E, "Request Error Exception");
        }
    }

    /**
     * 是否提示错误信息
     */
    public CodeErrorListener showCodeConstant(boolean isShowCodeConstant) {
        this.isShowCodeConstant = isShowCodeConstant;
        return this;
    }

    /**
     * 是否在符合条件时执行跳转登录界面操作
     */
    public CodeErrorListener setComeLogin(boolean comeLogin) {
        this.comeLogin = comeLogin;
        return this;
    }

    public boolean onCodeError(int resultCode) {
        return false;
    }
}
