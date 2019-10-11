package com.kylin.libs.mvpbase;

import android.content.Context;

/**
 *  MVP-View基类借口
 * @author  Kylin
 * @date 2018/3/30.
 */
public interface BaseView {
    /**
     * 显示加载进度或提示
     */
    void showLoading();
    /**
     * 隐藏加载进度或提示
     */
    void hideLoading();
    /**
     * 显示错误信息
     */
    void showError();

    /**
     * 获取当前Context
     * @return
     */
    Context getContext();
}
