package com.liuxd.firstblood.ui.news;

import com.liuxd.firstblood.entity.News;

/**
 * Created by Liuxd on 2016/11/22 11:01.
 */

public class NewsListPresenter implements NewsListContract.Presenter, OnDataLoadListener<News> {
    private NewsListContract.View mView;
    private NewsListContract.Model mModel;

    public NewsListPresenter(NewsListContract.View view) {
        this.mView = view;
        mModel = new NewsListModel(this);
    }

    @Override
    public void loadNews(String type) {
        mView.showLoading();
        mModel.loadNews(type);
    }

    @Override
    public void onFailed(Throwable throwable) {
        mView.dismissLoading();
        mView.showError();
    }

    @Override
    public void onSuccess(News data) {
        mView.dismissLoading();
        if (data.getData().size() == 0) {
            mView.showEmpty();
        } else {
            mView.showNews(data.getData());
        }
    }

    @Override
    public void attachView() {

    }

    @Override
    public void detachView() {
        if (mView != null)
            mView = null;
    }
}
