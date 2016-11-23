package com.liuxd.firstblood.ui.news;

import com.liuxd.firstblood.entity.News;
import com.liuxd.firstblood.network.HttpUtil;

import rx.Subscriber;

/**
 * Created by Liuxd on 2016/11/22 11:11.
 */

public class NewsListModel implements NewsListContract.Model {
    private OnDataLoadListener<News> mLoadListener;

    public NewsListModel(OnDataLoadListener<News> listener) {
        mLoadListener = listener;
    }

    @Override
    public void loadNews(String type) {
        HttpUtil.getInstance().getNewsByType(type, new Subscriber<News>() {
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
        });

    }
}
