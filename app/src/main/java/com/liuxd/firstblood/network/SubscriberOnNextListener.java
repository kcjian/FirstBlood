package com.liuxd.firstblood.network;

/**
 * Created by Liuxd on 2016/11/22 13:35.
 */

public interface SubscriberOnNextListener<T> {
    void onNext(T t);
}
