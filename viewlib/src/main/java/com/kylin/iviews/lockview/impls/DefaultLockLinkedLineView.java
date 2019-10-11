package com.kylin.iviews.lockview.impls;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;

import com.kylin.iviews.lockview.interfaces.ILockLinkedLineView;
import com.kylin.iviews.lockview.models.CellBean;
import com.kylin.iviews.lockview.utils.Configs;

import java.util.List;

/**
 * 默认单元格中 正常状态画法实现类
 * Created by kylin on 2018/5/7.
 */
public class DefaultLockLinkedLineView implements ILockLinkedLineView {
    @ColorInt
    private int normalColor;
    @ColorInt
    private int errorColor;

    private Paint paint;
    private float lineWidth = 2;

    public DefaultLockLinkedLineView() {
        this.paint = Configs.getPaint();
        this.paint.setStyle(Paint.Style.STROKE);
    }

    public int getNormalColor() {
        return normalColor;
    }

    public DefaultLockLinkedLineView setNormalColor(int normalColor) {
        this.normalColor = normalColor;
        return this;
    }

    public int getErrorColor() {
        return errorColor;
    }

    public DefaultLockLinkedLineView setErrorColor(int errorColor) {
        this.errorColor = errorColor;
        return this;
    }

    public float getLineWidth() {
        return lineWidth;
    }

    public DefaultLockLinkedLineView setLineWidth(float lineWidth) {
        this.lineWidth = lineWidth;
        return this;
    }

    @ColorInt
    private int getColor(boolean isError) {
        return isError ? this.getErrorColor() : this.getNormalColor();
    }

    @Override
    public void draw(@NonNull Canvas canvas,
                     @NonNull List<CellBean> cellBeanList, @NonNull List<Integer> selectList,
                     float endX, float endY, boolean isError) {
        if (selectList == null || selectList.isEmpty() || cellBeanList == null || cellBeanList.isEmpty()) {
            return;
        }

        final int saveCount = canvas.save();
        final Path path = new Path();

        CellBean firstCell = cellBeanList.get(selectList.get(0));
        path.moveTo(firstCell.x, firstCell.y);
        for (int i = 1; i < selectList.size(); i++) {
            CellBean cellBean = cellBeanList.get(selectList.get(i));

            path.lineTo(cellBean.x, cellBean.y);
        }

        if ((endX != 0 || endY != 0) && selectList.size() < cellBeanList.size()) {
            //画延长线
            path.lineTo(endX, endY);
        }

        this.paint.setColor(this.getColor(isError));
        this.paint.setStrokeWidth(this.getLineWidth());

        canvas.drawPath(path, this.paint);

        canvas.restoreToCount(saveCount);
    }
}
