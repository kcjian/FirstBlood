package com.liuxd.firstblood.network;

import com.liuxd.firstblood.entity.HttpResult;

/**
 * Created by Liuxd on 2016/11/22 11:19.
 * 请求异常
 */

public class ApiException extends RuntimeException {
    public ApiException(HttpResult httpResult) {
        this(httpResult.getReason());
    }

    private ApiException(String detailMessage) {
        super(detailMessage);
    }
}
