package com.liuxd.firstblood.network;

import com.liuxd.firstblood.entity.News;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Liuxd on 2016/11/22 13:17.
 */

public class HttpUtil extends RetrofitUtil {

    private HttpUtil() {
        super();
    }

    private static class HttpUtilSingleton {
        private static final HttpUtil INSTANCE = new HttpUtil();
    }

    public static HttpUtil getInstance() {
        return HttpUtilSingleton.INSTANCE;
    }

    public void getNewsByType(String type, Subscriber<News> subscriber) {
        Observable<News> observable = getApiService().getNewsByType(type)
                .map(new HttpResultFunction<News>());
        setSubscribe(observable, subscriber);
    }

    private <T> void setSubscribe(Observable<T> observable, Subscriber<T> subscriber) {
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//回调到主线程
                .subscribe(subscriber);
    }
}
