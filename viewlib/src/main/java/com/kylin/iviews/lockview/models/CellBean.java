package com.kylin.iviews.lockview.models;

/**
 * 单元格封装
 * Created by kylin on 2018/5/7.
 */

public class CellBean {
    /**  当前单元格ID */
    public int  id;
    /**  当前单元格坐标x */
    public float  x;
    /**  当前单元格坐标y */
    public float  y;
    /**  当前单元格半径r */
    public float  r;
    /**  当前单元格选中状态*/
    public boolean  isSelected;


    public CellBean(int id, float x, float y, float r) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.r = r;
    }

    /**
     * 判断当前单元格是否被选中
     * @param x 触摸点位坐标x
     * @param y 触摸点位坐标y
     * @return true:选中， false:没选中
     */
    public boolean selected(float x, float y){
        final float dx = this.x - x;
        final float dy = this.y - y;
        final double distance = Math.sqrt(dx * dx + dy * dy);

        return distance <= this.r;
    }
}
