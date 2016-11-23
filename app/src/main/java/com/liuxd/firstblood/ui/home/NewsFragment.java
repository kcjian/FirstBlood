package com.liuxd.firstblood.ui.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.liuxd.firstblood.R;
import com.liuxd.firstblood.ui.adapter.CommonPagerAdapter;
import com.liuxd.firstblood.ui.base.BaseFragment;
import com.liuxd.firstblood.ui.news.NewsListFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindArray;
import butterknife.BindView;

/**
 * Created by Liuxd on 2016/11/22 9:53.
 * 新闻
 */

public class NewsFragment extends BaseFragment {
    @BindView(R.id.tab_newsType)
    TabLayout mTabNewsType;
    @BindView(R.id.vp_news)
    ViewPager mVpNews;
    @BindArray(R.array.news_type)
    String[] mTypes;
    @BindArray(R.array.news_type_id)
    String[] mTypeIds;

    private CommonPagerAdapter<NewsListFragment> mAdapter;
    private List<NewsListFragment> fragments;

    @Override
    public int setLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    public void init(@Nullable Bundle savedInstanceState) {
        initFragments();
        mAdapter = new CommonPagerAdapter<NewsListFragment>(getChildFragmentManager(), fragments, mTypes);
        mVpNews.setOffscreenPageLimit(3);
        mVpNews.setAdapter(mAdapter);
        mTabNewsType.setupWithViewPager(mVpNews);
    }

    private void initFragments() {
        fragments = new ArrayList<>();
        for (int i = 0; i < mTypeIds.length; i++) {
            fragments.add(NewsListFragment.createInstance(mTypeIds[i]));
        }
    }

}
