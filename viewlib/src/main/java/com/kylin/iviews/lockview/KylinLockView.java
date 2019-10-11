package com.kylin.iviews.lockview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.kylin.iviews.R;
import com.kylin.iviews.lockview.impls.DefaultLockLinkedLineView;
import com.kylin.iviews.lockview.impls.DefaultLockNormalCellView;
import com.kylin.iviews.lockview.impls.DefaultLockSelectedCellView;
import com.kylin.iviews.lockview.listeners.OnLockViewSelectStatedChanged;
import com.kylin.iviews.lockview.models.CellBean;
import com.kylin.iviews.lockview.models.CellBeanFactory;
import com.kylin.iviews.lockview.utils.Configs;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kylin on 2018/5/7.
 */

public class KylinLockView extends View {
    private static final String TAG = "KylinLockView";
    @ColorInt
    private int normalColor;
    @ColorInt
    private int fillColor;
    @ColorInt
    private int selectedColor;
    @ColorInt
    private int errorColor;
    private float lineWidth;

    private int rowNum;
    private int colNum;

    private float endX, endY;
    private int selectSize = 0;
    private boolean isError;
    private List<CellBean> cellBeanList;
    private List<Integer> selectedList;

    private OnLockViewSelectStatedChanged listener;

    private DefaultLockNormalCellView normalCellView;
    private DefaultLockSelectedCellView selectedCellView;
    private DefaultLockLinkedLineView linkedLineView;

    public KylinLockView(Context context) {
        this(context, null);
    }

    public KylinLockView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public KylinLockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.init(context, attrs, defStyleAttr);
    }

    public void setOnLockViewSelectStatedChanged(OnLockViewSelectStatedChanged listener) {
        this.listener = listener;
    }

    /**
     * 初始化
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    private void init(Context context, @NonNull AttributeSet attrs, int defStyleAttr) {
        this.initAttrs(context, attrs, defStyleAttr);
        this.initDatas();
    }

    private void initDatas() {
        cellBeanList = new ArrayList<CellBean>();
        selectedList = new ArrayList<Integer>();

        this.buildWithStyle();
    }

    private void buildWithStyle() {
        this.setNormalCellView(new DefaultLockNormalCellView()
                .setNormalColor(this.getNormalColor())
                .setFillColor(this.getFillColor())
                .setLineWidth(this.getLineWidth())
        ).setSelectedCellView(new DefaultLockSelectedCellView()
                .setSelectColor(this.getSelectedColor())
                .setFillColor(this.getFillColor())
                .setErrorColor(this.getErrorColor())
                .setLineWidth(this.getLineWidth())
        ).setLinkedLineView(new DefaultLockLinkedLineView()
                .setNormalColor(this.getNormalColor())
                .setErrorColor(this.getErrorColor())
                .setLineWidth(this.getLineWidth())
        ).build();
    }

    private void build() {
        if (this.getNormalCellView() == null) {
            Log.e(TAG, "NormalCellView is null");
            return;
        }
        if (this.getSelectedCellView() == null) {
            Log.e(TAG, "SelectedCellView is null");
            return;
        }
        if (this.getLinkedLineView() == null) {
            Log.w(TAG, "LinkedLineView is null");
        }
        postInvalidate();
    }

    private void initAttrs(Context context, @NonNull AttributeSet attrs, int defStyleAttr) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.KylinLockView, defStyleAttr, 0);

        this.normalColor = ta.getColor(R.styleable.KylinLockView_klv_normal_color, Configs.getDefaultNormalColor());
        this.fillColor = ta.getColor(R.styleable.KylinLockView_klv_fill_color, Configs.getDefaultFillColor());
        this.selectedColor = ta.getColor(R.styleable.KylinLockView_klv_selected_color, Configs.getDefaultSelectedColor());
        this.errorColor = ta.getColor(R.styleable.KylinLockView_klv_error_color, Configs.getDefaultErrorColor());
        this.lineWidth = ta.getDimension(R.styleable.KylinLockView_klv_line_width, Configs.getDefaultLineWidth(context));

        this.rowNum = ta.getInteger(R.styleable.KylinLockView_klv_row, Configs.getDefaultRowNum());
        this.colNum = ta.getInteger(R.styleable.KylinLockView_klv_col, Configs.getDefaultColNum());

        ta.recycle();

        this.setNormalColor(Configs.getDefaultNormalColor());
        this.setFillColor(Configs.getDefaultFillColor());
        this.setSelectedColor(Configs.getDefaultSelectedColor());
        this.setErrorColor(Configs.getDefaultErrorColor());
        this.setLineWidth(Configs.getDefaultLineWidth(context));

        this.buildWithStyle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int w = Math.min(getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec), getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec));
        Log.d("kylin", "onMeasure:" + w);
//        super.onMeasure(w, w);

        setMeasuredDimension(w, w);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.d("kylin", "onLayout:");
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.d("kylin", "onSizeChanged:" + w);
        clearSelectedState();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.cellBeanList == null || this.cellBeanList.size() <= 0) {
            Log.d("kylin", "onDraw:" + getWidth() + "==" + getHeight());
            this.cellBeanList = new CellBeanFactory(this.rowNum, this.colNum,
                    getWidth(), getHeight()).getCellBeanList();
        }
        drawLinkLine(canvas);
        drawCells(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!isEnabled()) {
            return super.onTouchEvent(event);
        }

        boolean isHandled = false;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                handleActionDown(event);
                isHandled = true;
                break;
            case MotionEvent.ACTION_MOVE:
                handleActionMove(event);
                isHandled = true;
                break;
            case MotionEvent.ACTION_UP:
                handleActionUp(event);
                isHandled = true;
                break;
            default:
                break;
        }

        postInvalidate();
        return isHandled ? true : super.onTouchEvent(event);
    }

    //------------------------------onTouch-----------------------------//
    private void handleActionDown(MotionEvent event) {
        clearSelectedList();
        updateSelectState(event);
        if (this.listener != null) {
            this.listener.onStart(this);
        }
    }

    private void handleActionMove(MotionEvent event) {
        updateSelectState(event);
        this.endX = event.getX();
        this.endY = event.getY();
        final int size = this.selectedList.size();
        if (this.listener != null && size != this.selectSize) {
            this.selectSize = size;
            //选中节点有变化
            this.listener.onChange(this, this.selectedList);
        }
    }

    private void handleActionUp(MotionEvent event) {
        updateSelectState(event);
        this.endX = 0;
        this.endY = 0;
        if (this.listener != null) {
            this.listener.onComplete(this, this.selectedList);
        }
        //clear selected
        if (this.selectedList.size() > 0) {
            clearSelected();
        }
    }

    private void clearSelected() {
        setEnabled(false);
        this.postDelayed(this.action, Configs.getDefaultDelayTime());
    }

    private final Runnable action = new Runnable() {
        @Override
        public void run() {
            setEnabled(true);
            clearSelectedState();
        }
    };

    /**
     * 清楚选中单元
     */
    private void clearSelectedState() {
        clearSelectedList();
        this.isError = false;
        if (this.listener != null) {
            this.listener.onClear(this);
        }
        postInvalidate();
    }

    @Override
    protected void onDetachedFromWindow() {
        this.setOnLongClickListener(null);
        this.removeCallbacks(this.action);
        super.onDetachedFromWindow();
    }

    /**
     * 清理已经点击单元格
     */
    private void clearSelectedList() {
        for (int i = 0; i < this.selectedList.size(); i++) {
            this.cellBeanList.get(this.selectedList.get(i)).isSelected = false;
        }

        this.selectedList.clear();
        this.selectSize = 0;
    }

    /**
     * 更新选择状态
     *
     * @param event
     */
    private void updateSelectState(MotionEvent event) {
        final float x = event.getX();
        final float y = event.getY();

        for (CellBean bean : this.cellBeanList) {
            if (!bean.isSelected && bean.selected(x, y)) {
                bean.isSelected = true;
                this.selectedList.add(bean.id);
            }
        }
    }


    //------------------------------onDraw-----------------------------//

    /**
     * 画单元格
     */
    private void drawCells(Canvas canvas) {
        if (getSelectedCellView() == null) {
            Log.e(TAG, "SelectedCellView is null");
            return;
        }
        if (getNormalCellView() == null) {
            Log.e(TAG, "NormalCellView is null");
            return;
        }

        for (int i = 0; i < this.cellBeanList.size(); i++) {
            final CellBean item = this.cellBeanList.get(i);
            if (item.isSelected) {
                this.getSelectedCellView().draw(canvas, item, this.isError);
            } else {
                this.getNormalCellView().draw(canvas, item);
            }
        }
    }

    /**
     * 画线
     *
     * @param canvas
     */
    private void drawLinkLine(Canvas canvas) {
        if ((getLinkedLineView() != null) && (this.selectedList != null && !this.selectedList.isEmpty())) {
            getLinkedLineView().draw(canvas, this.cellBeanList, this.selectedList, this.endX, this.endY, this.isError);
        }
    }
    //------------------------------set--get-----------------------------//

    public int getNormalColor() {
        return normalColor;
    }

    public KylinLockView setNormalColor(int normalColor) {
        this.normalColor = normalColor;
        return this;
    }

    public int getFillColor() {
        return fillColor;
    }

    public KylinLockView setFillColor(int fillColor) {
        this.fillColor = fillColor;
        return this;
    }

    public int getSelectedColor() {
        return selectedColor;
    }

    public KylinLockView setSelectedColor(int selectedColor) {
        this.selectedColor = selectedColor;
        return this;
    }

    public int getErrorColor() {
        return errorColor;
    }

    public KylinLockView setErrorColor(int errorColor) {
        this.errorColor = errorColor;
        return this;
    }

    public float getLineWidth() {
        return lineWidth;
    }

    public KylinLockView setLineWidth(float lineWidth) {
        this.lineWidth = lineWidth;
        return this;
    }

    public void setRowColNum(int colNum, int rowNum) {
        this.rowNum = rowNum;
        this.colNum = colNum;

        this.selectedList.clear();
        this.selectSize = 0;

        this.cellBeanList.clear();

        this.cellBeanList = new CellBeanFactory(this.rowNum, this.colNum,
                getWidth(), getHeight()).getCellBeanList();
        postInvalidate();
    }

    public DefaultLockNormalCellView getNormalCellView() {
        return normalCellView;
    }

    public KylinLockView setNormalCellView(DefaultLockNormalCellView normalCellView) {
        this.normalCellView = normalCellView;
        return this;
    }

    public DefaultLockSelectedCellView getSelectedCellView() {
        return selectedCellView;
    }

    public KylinLockView setSelectedCellView(DefaultLockSelectedCellView selectedCellView) {
        this.selectedCellView = selectedCellView;
        return this;
    }

    public DefaultLockLinkedLineView getLinkedLineView() {
        return linkedLineView;
    }

    public KylinLockView setLinkedLineView(DefaultLockLinkedLineView linkedLineView) {
        this.linkedLineView = linkedLineView;
        return this;
    }
}
