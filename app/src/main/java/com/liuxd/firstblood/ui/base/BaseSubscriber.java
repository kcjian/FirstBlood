package com.liuxd.firstblood.ui.base;

import rx.Subscriber;

/**
 * Created by Liuxd on 2016/11/30 14:18.
 * 网络请求前判断是否有网
 */

public abstract class BaseSubscriber<T> extends Subscriber<T> {
    public static final String MSG = "No NetWork";

    @Override
    public void onStart() {
        super.onStart();
        //判断是否有网
//        if (!DeviceUtil.isNetworkAvailable()) {
//            onError(new RuntimeException(MSG));
//            onCompleted();
//        }
    }
}
