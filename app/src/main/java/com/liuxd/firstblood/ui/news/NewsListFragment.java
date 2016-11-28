package com.liuxd.firstblood.ui.news;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.liuxd.firstblood.R;
import com.liuxd.firstblood.constant.Constant;
import com.liuxd.firstblood.entity.News;
import com.liuxd.firstblood.ui.base.BaseAdapter;
import com.liuxd.firstblood.ui.base.BaseFragment;
import com.liuxd.firstblood.ui.base.BaseViewHolder;
import com.liuxd.firstblood.widget.view.SpacesItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Liuxd on 2016/11/21 16:41.
 * 新闻列表
 */

public class NewsListFragment extends BaseFragment implements NewsListContract.View {

    @BindView(R.id.rv_news)
    RecyclerView mRvNews;
    @BindView(R.id.srl_news)
    SwipeRefreshLayout mSrlNews;
    @BindView(R.id.layout_loadMore)
    LinearLayout mLayoutLoadMore;

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
        mLayoutLoadMore.setVisibility(View.GONE);
        mSrlNews.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE);
        mPresenter = new NewsListPresenter(this);
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
        mSrlNews.post(new Runnable() {
            @Override
            public void run() {
                mSrlNews.setRefreshing(true);
            }
        });
        mSrlNews.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.loadNews(getArguments().getString(Constant.BundleName.TYPE_NEWS));
            }
        });
        mPresenter.loadNews(getArguments().getString(Constant.BundleName.TYPE_NEWS));
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {
        mSrlNews.setRefreshing(false);
    }

    @Override
    public void showError() {
        mSrlNews.setRefreshing(false);
    }

    @Override
    public void showEmpty() {
        mSrlNews.setRefreshing(false);
    }

    @Override
    public void showNews(List<News.NewsBody> data) {
        mAdapter.setDatas(data, false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
