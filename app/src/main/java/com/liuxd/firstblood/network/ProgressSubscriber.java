package com.liuxd.firstblood.network;

import android.content.Context;
import android.widget.Toast;

import rx.Subscriber;

/**
 * Created by Liuxd on 2016/11/22 13:36.
 */

public class ProgressSubscriber<T> extends Subscriber<T> {

    private SubscriberOnNextListener<T> mSubscriberOnNextListener;
    private Context context;

    public ProgressSubscriber(SubscriberOnNextListener<T> mSubscriberOnNextListener, Context context) {
        this.mSubscriberOnNextListener = mSubscriberOnNextListener;
        this.context = context;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onCompleted() {
        Toast.makeText(context, "Get Top Movie Completed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(Throwable e) {
        Toast.makeText(context, "error:" + e.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNext(T t) {
        mSubscriberOnNextListener.onNext(t);
    }
}
