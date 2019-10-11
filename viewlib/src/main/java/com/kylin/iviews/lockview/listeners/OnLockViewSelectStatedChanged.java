package com.kylin.iviews.lockview.listeners;

import com.kylin.iviews.lockview.KylinLockView;

import java.util.List;

/**
 * 解锁画面回调接口
 * Created by kylin on 2018/5/7.
 */

public interface OnLockViewSelectStatedChanged {
    /**
     * 开始触摸时候回调方法
     * @param t
     * @param <T>
     */
    <T extends KylinLockView> void onStart(T t);

    /**
     * 当选中单元格发生变化回调方法
     * @param t
     * @param selectedList
     * @param <T>
     */
    <T extends KylinLockView> void onChange(T t, List<Integer> selectedList);

    /**
     * 选取完成时候回调方法
     * @param t
     * @param selectedList
     * @param <T>
     */
    <T extends KylinLockView> void onComplete(T t, List<Integer> selectedList);

    /**
     * 已经选中的单元格被清除之后的回调方法
     * @param t
     * @param <T>
     */
    <T extends KylinLockView> void onClear(T t);
}


