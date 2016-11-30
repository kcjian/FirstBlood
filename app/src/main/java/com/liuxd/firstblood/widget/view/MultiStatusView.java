package com.liuxd.firstblood.widget.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.provider.Settings;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.liuxd.firstblood.R;
import com.liuxd.firstblood.widget.NoDoubleClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Liuxd on 2016/11/29 18:10.
 * 自定义多状态（空数据、错误、加载中）视图
 */

public class MultiStatusView extends FrameLayout {
    private LinearLayout mEmptyView;
    private LinearLayout mErrorView;
    private LinearLayout mLoadingView;
    private LinearLayout mNoNetworkView;

    private int mEmptyViewResId;
    private int mErrorViewResId;
    private int mLoadingViewResId;
    private int mNoNetworkViewResId;

    private static final String TAG_EMPTY = "tag_empty_view";
    private static final String TAG_ERROR = "tag_error_view";
    private static final String TAG_LOADING = "tag_loading_view";
    private static final String TAG_NO_NETWORK = "tag_no_network_view";
    private static final String TAG_CONTENT = "tag_content_view";

    private List<View> mViews = new ArrayList<>();
    ;
    private LayoutInflater mLayoutInflater;

    private Context mContext;
    private OnClickListener mOnRetryClickListener;

    public MultiStatusView(Context context) {
        this(context, null);
    }

    public MultiStatusView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MultiStatusView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public MultiStatusView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(context);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MultiStatusView, defStyleAttr, defStyleRes);
        this.mEmptyViewResId = a.getResourceId(R.styleable.MultiStatusView_emptyView, R.layout.layout_empty);
        this.mErrorViewResId = a.getResourceId(R.styleable.MultiStatusView_errorView, R.layout.layout_error);
        this.mNoNetworkViewResId = a.getResourceId(R.styleable.MultiStatusView_noNetworkView, R.layout.layout_no_network);
        this.mLoadingViewResId = a.getResourceId(R.styleable.MultiStatusView_loadingView, R.layout.layout_loading);
        a.recycle();
    }

    @Override
    public void addView(View child) {
        super.addView(child);
        if (child.getTag() == null || (!child.getTag().equals(TAG_LOADING) &&
                !child.getTag().equals(TAG_EMPTY) && !child.getTag().equals(TAG_ERROR))
                && !child.getTag().equals(TAG_NO_NETWORK)) {
            child.setTag(TAG_CONTENT);
            mViews.add(child);
        }
    }

    public void showEmpty() {
        if (mEmptyView == null) {
            mEmptyView = (LinearLayout) mLayoutInflater.inflate(mEmptyViewResId, null)
                    .findViewById(R.id.emptyViewLayout);
            if (mOnRetryClickListener != null) {
                Button btn_load = (Button) mEmptyView.findViewById(R.id.btn_load);
                btn_load.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showLoading();
                        mOnRetryClickListener.onClick(v);
                    }
                });
            }
            mEmptyView.setTag(TAG_EMPTY);
            addView(mEmptyView);
            mViews.add(mEmptyView);
        } else {
            showView(TAG_EMPTY);
        }
    }

    public void showError() {
        if (mErrorView == null) {
            mErrorView = (LinearLayout) mLayoutInflater.inflate(mErrorViewResId, null)
                    .findViewById(R.id.errorViewLayout);
            if (mOnRetryClickListener != null) {
                Button btn_retry = (Button) mErrorView.findViewById(R.id.btn_retry);
                btn_retry.setOnClickListener(new NoDoubleClickListener() {

                    @Override
                    public void onNoDoubleClick(View v) {
                        showLoading();
                        mOnRetryClickListener.onClick(v);
                    }
                });
            }
            mErrorView.setTag(TAG_ERROR);
            addView(mErrorView);
            mViews.add(mErrorView);
        } else {
            showView(TAG_ERROR);
        }
    }

    public void showNoNetwork() {
        if (mNoNetworkView == null) {
            mNoNetworkView = (LinearLayout) mLayoutInflater.inflate(mNoNetworkViewResId, null)
                    .findViewById(R.id.noNetworkViewLayout);
            Button btn_setNetWork = (Button) mNoNetworkView.findViewById(R.id.btn_setNetWork);
            btn_setNetWork.setOnClickListener(new NoDoubleClickListener() {
                @Override
                public void onNoDoubleClick(View v) {
                    Intent intent = new Intent(Settings.ACTION_SETTINGS);
                    mContext.startActivity(intent);
                }
            });
            mNoNetworkView.setTag(TAG_NO_NETWORK);
            addView(mNoNetworkView);
            mViews.add(mNoNetworkView);
        } else {
            showView(TAG_NO_NETWORK);
        }
    }

    public void showLoading() {
        if (mLoadingView == null) {
            mLoadingView = (LinearLayout) mLayoutInflater.inflate(mLoadingViewResId, null)
                    .findViewById(R.id.loadingViewLayout);
            mLoadingView.setTag(TAG_LOADING);
            addView(mLoadingView);
            mViews.add(mLoadingView);
        } else {
            showView(TAG_LOADING);
        }
    }

    public void showContent() {
        showView(TAG_CONTENT);
    }

    private void showView(String tag) {
        for (View v : mViews) {
            v.setVisibility(tag.equals(v.getTag()) ? View.VISIBLE : View.GONE);
        }
    }

    public void setOnRetryClickListener(OnClickListener onClickListener) {
        this.mOnRetryClickListener = onClickListener;
    }
}
