package com.liuxd.firstblood.ui.base;

import com.liuxd.firstblood.util.DeviceUtil;

import rx.Subscriber;

/**
 * Created by Liuxd on 2016/11/30 14:18.
 * 网络请求前判断是否有网（弃用）
 */
@Deprecated
public abstract class BaseSubscriber<T> extends Subscriber<T> {
    public static final String MSG = "No NetWork";

    @Override
    public void onStart() {
        super.onStart();
        //判断是否有网
        if (!DeviceUtil.isNetworkAvailable()) {
            onError(new RuntimeException(MSG));
            onCompleted();//这里并没有把后面的 onError() 拦截掉，不用了
        }
    }
}
