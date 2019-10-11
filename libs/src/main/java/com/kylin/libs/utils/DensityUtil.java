package com.kylin.libs.utils;

import android.util.TypedValue;

import com.kylin.libs.app.applications.KylinApplication;

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
    public static int px2dip(float pxValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,
                pxValue, KylinApplication.mAppContext.getResources().getDisplayMetrics());
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     *
     * @param dipValue
     * @return
     */
    public static int dip2px(float dipValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dipValue, KylinApplication.mAppContext.getResources().getDisplayMetrics());
    }
    /**
     * 将sp或dp值转换为px值，保证尺寸大小不变
     *
     * @param dipValue
     * @return
     */
    public static int sp2px(float dipValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                dipValue, KylinApplication.mAppContext.getResources().getDisplayMetrics());
    }

    /**
     * px转dp
     * @param pxVal
     * @return
     */

    public static float px2dp(float pxVal) {
        final float scale = KylinApplication.mAppContext.getResources().getDisplayMetrics().density;
        return (pxVal / scale);
    }


    /**
     * px转sp
     * @param pxVal
     * @return
     */

    public static float px2sp(float pxVal) {
        return (pxVal / KylinApplication.mAppContext.getResources().getDisplayMetrics().scaledDensity);

    }
}
