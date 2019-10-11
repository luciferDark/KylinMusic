package com.kylin.iviews.lockview.impls;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;

import com.kylin.iviews.lockview.interfaces.ISelectedCellView;
import com.kylin.iviews.lockview.models.CellBean;
import com.kylin.iviews.lockview.utils.Configs;

/**
 * 默认单元格中 正常状态画法实现类
 * Created by kylin on 2018/5/7.
 */
public class DefaultLockSelectedCellView implements ISelectedCellView {
    @ColorInt
    private int selectColor;
    @ColorInt
    private int errorColor;
    @ColorInt
    private int fillColor;

    private Paint paint;
    private float lineWidth = 2;

    public DefaultLockSelectedCellView() {
        this.paint = Configs.getPaint();
    }

    public int getSelectColor() {
        return selectColor;
    }

    public DefaultLockSelectedCellView setSelectColor(int selectColor) {
        this.selectColor = selectColor;
        return this;
    }

    public int getFillColor() {
        return fillColor;
    }

    public DefaultLockSelectedCellView setFillColor(int fillColor) {
        this.fillColor = fillColor;
        return this;
    }

    public int getErrorColor() {
        return errorColor;
    }

    public DefaultLockSelectedCellView setErrorColor(int errorColor) {
        this.errorColor = errorColor;
        return this;
    }

    public Paint getPaint() {
        return paint;
    }

    public DefaultLockSelectedCellView setPaint(Paint paint) {
        this.paint = paint;
        return this;
    }

    public float getLineWidth() {
        return lineWidth;
    }

    public DefaultLockSelectedCellView setLineWidth(float lineWidth) {
        this.lineWidth = lineWidth;
        return this;
    }

    @ColorInt
    public int getColor(boolean isError) {
        return isError ? this.getErrorColor() : this.getSelectColor();
    }

    @Override
    public void draw(@NonNull Canvas canvas, @NonNull CellBean cellBean, @NonNull boolean isError) {
        final int saveCount = canvas.save();
        //画外围圈
        this.paint.setColor(this.getColor(isError));
        canvas.drawCircle(cellBean.x, cellBean.y, cellBean.r, this.paint);

        //画内部圈，半径是外围圈和线宽的差
        this.paint.setColor(this.getFillColor());
        canvas.drawCircle(cellBean.x, cellBean.y, cellBean.r - this.getLineWidth(), this.paint);

        //画内部小圈
        this.paint.setColor(this.getColor(isError));
        canvas.drawCircle(cellBean.x, cellBean.y, cellBean.r /5f, this.paint);

        canvas.restoreToCount(saveCount);
    }
}
