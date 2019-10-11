package com.kylin.iviews.lockview.utils;

import android.content.Context;
import android.util.TypedValue;

/**
 * 分辨率处理工具
 * @author  Kylin
 * @date 2018/3/30.
 */
public class DensityUtil {
    /**
     * 将px值转换为dip或dp值，保证尺寸大小不变
     *
     * @param pxValue
     * @return
     */
    public static int px2dip(float pxValue, Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,
                pxValue, context.getResources().getDisplayMetrics());
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     *
     * @param dipValue
     * @return
     */
    public static int dip2px(float dipValue, Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dipValue, context.getResources().getDisplayMetrics());
    }
    /**
     * 将sp或dp值转换为px值，保证尺寸大小不变
     *
     * @param dipValue
     * @return
     */
    public static int sp2px(float dipValue, Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                dipValue, context.getResources().getDisplayMetrics());
    }

    /**
     * px转dp
     * @param pxVal
     * @return
     */

    public static float px2dp(float pxVal, Context context) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (pxVal / scale);
    }


    /**
     * px转sp
     * @param pxVal
     * @return
     */

    public static float px2sp(float pxVal, Context context) {
        return (pxVal / context.getResources().getDisplayMetrics().scaledDensity);

    }
}
