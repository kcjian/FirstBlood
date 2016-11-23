package com.liuxd.firstblood.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Liuxd on 2016/11/21 16:01.
 * 网络请求返回结果泛型封装
 */

public class HttpResult<T> {
    @SerializedName("error_code")
    private int errorCode;
    private String reason;
    private T result;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
