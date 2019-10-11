package com.kylin.iviews.lockview.interfaces;

import android.graphics.Canvas;
import android.support.annotation.NonNull;

import com.kylin.iviews.lockview.models.CellBean;

/**
 * 命中单元格视图接口
 * Created by kylin on 2018/5/7.
 */
public interface ISelectedCellView {

    /**
     * 自定义命中的单元格视图画法
     *
     * @param canvas   画布
     * @param cellBean 单元格数据
     * @param isError  是否命中错误
     */
    void draw(@NonNull Canvas canvas, @NonNull CellBean cellBean, @NonNull boolean isError);
}
