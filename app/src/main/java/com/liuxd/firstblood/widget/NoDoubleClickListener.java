package com.liuxd.firstblood.widget;

import android.view.View;

import java.util.Calendar;

/**
 * Created by Liuxd on 2016/11/21 10:41.
 * 防止连续的快速点击出发事件的监听器
 */

public abstract class NoDoubleClickListener implements View.OnClickListener {
    private static final int CLICK_DELAY_TIME_MIN = 1000;
    private long lastClickTime = 0;

    @Override
    public void onClick(View view) {
        long currentTime = Calendar.getInstance().getTimeInMillis();
        if (currentTime - lastClickTime > CLICK_DELAY_TIME_MIN) {
            lastClickTime = currentTime;
            onNoDoubleClick(view);
        }
    }

    public abstract void onNoDoubleClick(View v);
}
