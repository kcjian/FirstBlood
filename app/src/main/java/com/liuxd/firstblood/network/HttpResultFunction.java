package com.liuxd.firstblood.network;

import com.liuxd.firstblood.entity.HttpResult;

import rx.functions.Func1;

/**
 * Created by Liuxd on 2016/11/22 11:14.
 * 请求结果统一处理方法
 */

public class HttpResultFunction<T> implements Func1<HttpResult<T>, T> {

    @Override
    public T call(HttpResult<T> httpResult) {
        if (httpResult.getErrorCode() != 0) {
            throw new ApiException(httpResult);
        }
        return httpResult.getResult();
    }
}
