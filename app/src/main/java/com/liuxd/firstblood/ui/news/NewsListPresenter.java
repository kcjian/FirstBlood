package com.liuxd.firstblood.ui.news;

import com.liuxd.firstblood.entity.News;
import com.liuxd.firstblood.util.DeviceUtil;

/**
 * Created by Liuxd on 2016/11/22 11:01.
 * 新闻列表 Presenter，负责调度 view 和 model
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
        mModel.loadNews(type);
    }

    @Override
    public void onFailed(Throwable throwable) {
        if (!DeviceUtil.isNetworkAvailable()) {
            mView.showNoNetwork();
        } else {
            mView.showError();
        }
    }

    @Override
    public void onSuccess(News data) {
        if (data.getData().size() == 0) {
            mView.showEmpty();
        } else {
            mView.hideLoading();
            mView.showNews(data.getData());
        }
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onDestroy() {
        if (mModel != null)
            mModel.unSubscribe();
        if (mView != null)
            mView = null;
    }
}
