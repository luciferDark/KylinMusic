package com.kylin.iviews.lockview.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.ColorInt;

/**
 * Created by kylin on 2018/5/7.
 */

public class Configs {
    private static final String DEFAULT_NORMAL_COLOR = "#2196F3";
    private static final String DEFAULT_SELECTED_COLOR = "#3F51B5";
    private static final String DEFAULT_ERROR_COLOR = "#F44336";
    private static final String DEFAULT_FILL_COLOR = "#FFFFFF";
    private static final int DEFAULT_LINE_WIDTH = 2;
    private static final int DEFAULT_DELAY_TIME = 1000;//ms


    private static final int DEFAULT_ROW_NUM = 3;
    private static final int DEFAULT_COL_NUM = 3;

    @ColorInt
    public static int getDefaultNormalColor() {
        return Color.parseColor(DEFAULT_NORMAL_COLOR);
    }

    @ColorInt
    public static int getDefaultSelectedColor() {
        return Color.parseColor(DEFAULT_SELECTED_COLOR);
    }

    @ColorInt
    public static int getDefaultErrorColor() {
        return Color.parseColor(DEFAULT_ERROR_COLOR);
    }

    @ColorInt
    public static int getDefaultFillColor() {
        return Color.parseColor(DEFAULT_FILL_COLOR);
    }

    public static int getDefaultLineWidth(Context context) {
        return DensityUtil.dip2px(DEFAULT_LINE_WIDTH, context);
    }

    public static int getDefaultDelayTime() {
        return DEFAULT_DELAY_TIME;
    }

    public static int getDefaultRowNum() {
        return DEFAULT_ROW_NUM;
    }

    public static int getDefaultColNum() {
        return DEFAULT_COL_NUM;
    }

    public static Paint getPaint() {
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStyle(Paint.Style.FILL);
        return paint;
    }
}
