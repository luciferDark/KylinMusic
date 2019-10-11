package com.kylin.iviews.lockview.impls;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;

import com.kylin.iviews.lockview.interfaces.INormalCellView;
import com.kylin.iviews.lockview.models.CellBean;
import com.kylin.iviews.lockview.utils.Configs;

/**
 * 默认单元格中 正常状态画法实现类
 * Created by kylin on 2018/5/7.
 */
public class DefaultLockNormalCellView implements INormalCellView {
    @ColorInt
    private int normalColor;
    @ColorInt
    private int fillColor;

    private Paint paint;
    private float lineWidth = 2;

    public DefaultLockNormalCellView() {
        this.paint = Configs.getPaint();
    }

    public int getNormalColor() {
        return normalColor;
    }

    public DefaultLockNormalCellView setNormalColor(int normalColor) {
        this.normalColor = normalColor;
        return this;
    }

    public int getFillColor() {
        return fillColor;
    }

    public DefaultLockNormalCellView setFillColor(int fillColor) {
        this.fillColor = fillColor;
        return this;
    }

    public float getLineWidth() {
        return lineWidth;
    }

    public DefaultLockNormalCellView setLineWidth(float lineWidth) {
        this.lineWidth = lineWidth;
        return this;
    }

    @Override
    public void draw(@NonNull Canvas canvas, @NonNull CellBean cellBean) {
        final int saveCount = canvas.save();
        //画外围圈
        this.paint.setColor(this.getNormalColor());
        canvas.drawCircle(cellBean.x, cellBean.y, cellBean.r, this.paint);
        //画内部圈，半径是外围圈和线宽的差
        this.paint.setColor(this.getFillColor());
        canvas.drawCircle(cellBean.x, cellBean.y, cellBean.r - this.getLineWidth(), this.paint);

        canvas.restoreToCount(saveCount);
    }
}
