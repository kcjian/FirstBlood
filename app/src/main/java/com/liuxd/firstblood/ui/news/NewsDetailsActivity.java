package com.liuxd.firstblood.ui.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.liuxd.firstblood.R;
import com.liuxd.firstblood.ui.base.BaseActivity;
import com.liuxd.firstblood.util.ImageLoadUtil;

import butterknife.BindView;

import static com.liuxd.firstblood.R.id.toolbar;

/**
 * Created by Liuxd on 2016/11/29 10:50.
 */

public class NewsDetailsActivity extends BaseActivity {
    @BindView(toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout mToolbarLayout;
    @BindView(R.id.appbar)
    AppBarLayout mAppbar;
    @BindView(R.id.progressBar_news)
    ProgressBar mProgressBarNews;
    @BindView(R.id.wv_news)
    WebView mWvNews;
    @BindView(R.id.iv_news)
    ImageView mIvNews;

    @Override
    public int setLayoutId() {
        return R.layout.activity_newsdetails;
    }

    @Override
    public void init(@Nullable Bundle savedInstanceState) {
        String mUrl = getIntent().getStringExtra("url");
        mIvNews.setTransitionName("transition_news_img");
        ImageLoadUtil.getInstance().load(this, getIntent().getStringExtra("imageUrl"), mIvNews);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbarLayout.setTitle(getIntent().getStringExtra("title"));
        WebSettings settings = mWvNews.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setAppCacheEnabled(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setSupportZoom(true);
//        mWvNews.setWebChromeClient(new ChromeClient());
//        mWvNews.setWebViewClient(new LoveClient());

        mWvNews.loadUrl(mUrl);
    }

}
