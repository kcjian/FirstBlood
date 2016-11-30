package com.liuxd.firstblood.ui.base;

/**
 * Created by Liuxd on 2016/11/22 15:12.
 */

public interface BaseView {
    void showLoading();

    void hideLoading();

    void showError();

    void showEmpty();

    void showNoNetwork();
}
