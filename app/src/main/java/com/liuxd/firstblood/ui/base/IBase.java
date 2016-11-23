package com.liuxd.firstblood.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by Liuxd on 2016/11/21 10:40.
 * 通用的 Activity 和 Fragment 接口
 */

public interface IBase {
    int setLayoutId();

    void init(@Nullable Bundle savedInstanceState);
}
