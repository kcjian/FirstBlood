package com.liuxd.firstblood.ui.news;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.liuxd.firstblood.R;
import com.liuxd.firstblood.constant.Constant;
import com.liuxd.firstblood.entity.News;
import com.liuxd.firstblood.ui.base.BaseAdapter;
import com.liuxd.firstblood.ui.base.BaseFragment;
import com.liuxd.firstblood.ui.base.BaseViewHolder;
import com.liuxd.firstblood.widget.view.MultiStatusView;
import com.liuxd.firstblood.widget.view.SpacesItemDecoration;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Liuxd on 2016/11/21 16:41.
 * 新闻列表
 */

public class NewsListFragment extends BaseFragment<NewsListPresenter> implements
        NewsListContract.View, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.rv_news)
    RecyclerView mRvNews;
    @BindView(R.id.srl_news)
    SwipeRefreshLayout mSrlNews;
    @BindView(R.id.layout_loadMore)
    LinearLayout mLayoutLoadMore;
    @BindView(R.id.multiStatusView)
    MultiStatusView mMultiStatusView;

    private BaseAdapter<News.NewsBody> mAdapter;

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
        mPresenter = new NewsListPresenter(this);
        mLayoutLoadMore.setVisibility(View.GONE);
        mMultiStatusView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRefresh();
            }
        });
        mSrlNews.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE);
        mSrlNews.setOnRefreshListener(this);
        initRecyclerView();
        onRefresh();
    }

    private void initRecyclerView() {
        mAdapter = new BaseAdapter<News.NewsBody>(null, R.layout.item_news) {
            @Override
            public void convert(BaseViewHolder holder, News.NewsBody data) {
                holder.setText(R.id.tv_title, data.getTitle());
                holder.setImageSrc(R.id.iv_thumbPic1, data.getThumbPic1());
                holder.setText(R.id.tv_authorName, data.getAuthorName());
                holder.setText(R.id.tv_date, data.getDate());
            }
        };
        mAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View v, int position) {
                Intent intent = new Intent(getActivity(), NewsDetailsActivity.class);
                intent.putExtra(Constant.BundleName.URL_NEWS, mAdapter.getDatas().get(position).getUrl());
                intent.putExtra(Constant.BundleName.URL_IMAGE_NEWS, mAdapter.getDatas().get(position).getThumbPic1());
                intent.putExtra(Constant.BundleName.TITLE_NEWS, mAdapter.getDatas().get(position).getTitle());

                View transitionView = v.findViewById(R.id.iv_thumbPic1);
                ActivityOptionsCompat options =
                        ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
                                transitionView, Constant.Parameters.TRANSITION_NEWS_IMG);

                ActivityCompat.startActivity(getActivity(), intent, options.toBundle());
            }
        });
        mRvNews.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mRvNews.addItemDecoration(new SpacesItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        mRvNews.setAdapter(mAdapter);
    }

    @Override
    public void showLoading() {
        mMultiStatusView.showLoading();
    }

    @Override
    public void hideLoading() {
        mMultiStatusView.showContent();
        mSrlNews.setRefreshing(false);
    }

    @Override
    public void showError() {
        mMultiStatusView.showError();
        mSrlNews.setRefreshing(false);
    }

    @Override
    public void showEmpty() {
        mMultiStatusView.showEmpty();
        mSrlNews.setRefreshing(false);
    }

    @Override
    public void showNoNetwork() {
        mMultiStatusView.showNoNetwork();
        mSrlNews.setRefreshing(false);
    }

    @Override
    public void showNews(List<News.NewsBody> data) {
        mAdapter.setDatas(data, false);
    }

    @Override
    public void onRefresh() {
        mPresenter.loadNews(getArguments().getString(Constant.BundleName.TYPE_NEWS));
    }
}
