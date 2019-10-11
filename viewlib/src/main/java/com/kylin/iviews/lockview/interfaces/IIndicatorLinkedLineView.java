package com.kylin.iviews.lockview.interfaces;

import android.graphics.Canvas;
import android.support.annotation.NonNull;

import com.kylin.iviews.lockview.models.CellBean;

import java.util.List;

/**
 * 自定义视图指示器连接直线接口
 * Created by kylin on 2018/5/7.
 */
public interface IIndicatorLinkedLineView {
    /**
     * 自定义视图指示器连接直线画法
     *
     * @param canvas   画布
     * @param cellBeanList 所有单元格数据
     * @param selectList 选中单元格index
     * @param isError  是否命中错误
     */
    void  draw(@NonNull Canvas canvas, @NonNull List<CellBean> cellBeanList,
               @NonNull List<Integer> selectList, boolean isError);
}
