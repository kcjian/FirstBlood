package com.liuxd.firstblood.widget;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

/**
 * Created by Liuxd on 2016/11/24 9:34.
 */

public class ScaleInAnimation {
    private static final float DEFAULT_SCALE_FROM = 0.5F;
    private final float mFrom;

    public ScaleInAnimation() {
        this(0.5F);
    }

    public ScaleInAnimation(float from) {
        this.mFrom = from;
    }

    public Animator[] getAnimators(View view) {
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", new float[]{this.mFrom, 1.0F});
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", new float[]{this.mFrom, 1.0F});
        return new ObjectAnimator[]{scaleX, scaleY};
    }
}
