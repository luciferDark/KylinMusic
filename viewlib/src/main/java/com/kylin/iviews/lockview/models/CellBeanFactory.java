package com.kylin.iviews.lockview.models;

import java.util.ArrayList;
import java.util.List;

/**
 * 单元格工厂
 * Created by kylin on 2018/5/7.
 */
public class CellBeanFactory {
    private static int CELL_ROW = 3;
    private static int CELL_COL = 3;

    /**
     * 控件的宽度
     */
    private int width;
    /**
     * 控件的高度
     */
    private int height;

    /**
     * 控件的单元格集合
     */
    private List<CellBean> cellBeanList;

    public CellBeanFactory(int rowNum, int colNum, int width, int height) {
        this.width = width;
        this.height = height;
        this.CELL_ROW = rowNum;
        this.CELL_COL = colNum;

        this.cellBeanList = new ArrayList<CellBean>();
        this.create();
    }

    /**
     * 创建单元格
     */
    private void create() {
        final float dw = this.width / (2f * (CELL_COL + 1)); //平均分割成n+1个圆的直径, 圆的半径为1/2*（n+1）的屏幕宽度.
        final float dh = this.height / (2f * (CELL_ROW + 1));


        final float location_dw = this.width / (2f * (CELL_COL));
        final float location_dh = this.height / (2f * (CELL_ROW));

        for (int i = 0; i < CELL_ROW; i++) {
            for (int j = 0; j < CELL_COL; j++) {
                this.cellBeanList.add(new CellBean(
                        i * CELL_COL + j,
                        (j * 2 + 1) * location_dw,
                        (i * 2 + 1) * location_dh,
                        Math.min(dw, dh)));
            }
        }
    }

    public List<CellBean> getCellBeanList() {
        return this.cellBeanList;
    }

}
