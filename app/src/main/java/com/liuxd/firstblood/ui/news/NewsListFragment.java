package com.liuxd.firstblood.ui.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.liuxd.firstblood.R;
import com.liuxd.firstblood.constant.Constant;
import com.liuxd.firstblood.entity.News;
import com.liuxd.firstblood.ui.base.BaseAdapter;
import com.liuxd.firstblood.ui.base.BaseFragment;
import com.liuxd.firstblood.ui.base.BaseViewHolder;
import com.liuxd.firstblood.view.SpacesItemDecoration;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Liuxd on 2016/11/21 16:41.
 * 新闻列表
 */

public class NewsListFragment extends BaseFragment implements NewsListContract.View {

    @BindView(R.id.rv_news)
    RecyclerView mRvNews;

    private BaseAdapter<News.NewsBody> mAdapter;

    private NewsListPresenter mPresenter;

    public NewsListFragment() {
    }

    public static NewsListFragment createInstance(String type) {
        NewsListFragment fragment = new NewsListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constant.BundleName.TYPE_NEWS, type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int setLayoutId() {
        return R.layout.fragment_newslist;
    }

    @Override
    public void init(@Nullable Bundle savedInstanceState) {
        mAdapter = new BaseAdapter<News.NewsBody>(null, R.layout.item_news) {
            @Override
            public void convert(BaseViewHolder holder, News.NewsBody data) {
                holder.setText(R.id.tv_title, data.getTitle());
                holder.setImageSrc(R.id.iv_thumbPic1, data.getThumbPic1());
//                holder.setImageSrc(R.id.iv_thumbPic2, data.getThumbPic2());
//                holder.setImageSrc(R.id.iv_thumbPic3, data.getThumbPic3());
                holder.setText(R.id.tv_authorName, data.getAuthorName());
                holder.setText(R.id.tv_date, data.getDate());
            }
        };
        mRvNews.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mRvNews.addItemDecoration(new SpacesItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        mRvNews.setAdapter(mAdapter);
    }

    @Override
    public void lazyRequest() {
        super.lazyRequest();
        mPresenter = new NewsListPresenter(this);
        mPresenter.loadNews(getArguments().getString(Constant.BundleName.TYPE_NEWS));
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showNews(List<News.NewsBody> data) {
        mAdapter.setDatas(data, false);
    }

}
