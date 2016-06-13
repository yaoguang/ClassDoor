package com.jshs.mobile.banmen.Http;

/**
 * Created by SZH on 2016/6/10.
 */
public class Result<T> {
    public int status;
    public String msg;
    public int code;
    public T data;

    public boolean isSuccess(int... codes) {
        if (status == ResultCodeConstants.SUCCESS)
            return true;
        return isContains(codes);
    }

    public boolean isContains(int... codes) {
        if (codes == null)
            return false;
        for (int i = 0; i < codes.length; i++) {
            if (status == codes[i])
                return true;
        }
        return false;
    }

    public Result getResult() {
        return this;
    }

    @Override
    public String toString() {
        return "Result{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", code=" + code +
                ", data=" + data +
                '}';
    }
}
