package com.liuxd.firstblood.ui.news;

import com.liuxd.firstblood.entity.News;
import com.liuxd.firstblood.network.HttpUtil;

import rx.Subscriber;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Liuxd on 2016/11/22 11:11.
 * 负责请求网络数据
 */

public class NewsListModel implements NewsListContract.Model {
    private OnDataLoadListener<News> mLoadListener;
    private CompositeSubscription mCompositeSubscription;

    public NewsListModel(OnDataLoadListener<News> listener) {
        mLoadListener = listener;
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void loadNews(String type) {
        Subscriber<News> mSubscriber = new Subscriber<News>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mLoadListener.onFailed(e);
            }

            @Override
            public void onNext(News news) {
                mLoadListener.onSuccess(news);
            }
        };
        mCompositeSubscription.add(mSubscriber);
        HttpUtil.getInstance().getNewsByType(type, mSubscriber);
    }

    @Override
    public void unSubscribe() {
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions())
            mCompositeSubscription.unsubscribe();
    }
}
