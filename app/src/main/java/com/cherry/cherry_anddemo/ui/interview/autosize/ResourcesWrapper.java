package com.cherry.cherry_anddemo.ui.interview.autosize;

import android.content.res.Resources;
import android.util.DisplayMetrics;

/**
 * 今日头条的适配方案
 * 可在activity中重写如下方法
 *
 * @Override
    public Resources getResources() {
        if (mResources == null) {
        final AutoSize autoSize = getAutoSize();
        if (autoSize != null) {
            mResources = new ResourcesWrapper(super.getResources(), autoSize);
        }
        }
        return mResources != null ? mResources : super.getResources();
    }

    protected AutoSize getAutoSize() {
        return new AutoSize(740, true);
    }
 *
 */
public class ResourcesWrapper extends Resources {
    private final AutoSize autoSize;
    private float targetDensity;
    private float targetScaledDensity;
    private int targetDensityDpi;

    public ResourcesWrapper(Resources resources, AutoSize autoSize) {
        super(resources.getAssets(), resources.getDisplayMetrics(), resources.getConfiguration());
        this.autoSize = autoSize;
    }

    @Override
    public DisplayMetrics getDisplayMetrics() {
        DisplayMetrics displayMetrics = super.getDisplayMetrics();
        initValue(displayMetrics);
        autoSize(displayMetrics);
        return displayMetrics;
    }

    private void initValue(DisplayMetrics displayMetrics) {
        if (targetDensity == 0) {
            float nonCompatDensity = displayMetrics.density;
            float nonCompatScaledDensity = displayMetrics.scaledDensity;
            float designSizeInDp = autoSize.designSizeInDp;
            if (designSizeInDp > 0) {
                targetDensity = displayMetrics.widthPixels / designSizeInDp;
            } else {
                targetDensity = displayMetrics.heightPixels / -designSizeInDp;
            }
            targetScaledDensity = targetDensity * (nonCompatScaledDensity / nonCompatDensity);
            targetDensityDpi = (int) (160 * targetDensity);
        }
    }

    private void autoSize(DisplayMetrics displayMetrics) {
        displayMetrics.density = targetDensity;
        displayMetrics.densityDpi = targetDensityDpi;
        if (autoSize.isSupportSp) {
            displayMetrics.scaledDensity = targetScaledDensity;
        }
    }
}







