package com.liuxd.firstblood.ui.news;

/**
 * Created by Liuxd on 2016/11/22 14:49.
 */

public interface OnDataLoadListener<T> {
    void onFailed(Throwable throwable);

    void onSuccess(T data);
}
