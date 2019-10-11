package com.kylin.ui.guid;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.kylin.R;
import com.kylin.libs.mvpbase.BasePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * 欢迎页面Presenter
 *
 * @author Kylin
 * @date 2018/3/31
 */
public class GuidPresenter extends BasePresenter<GuidModel, GuidActivity> {
    /** 显示图片id*/
    public int[] mDuidVpId = new int[]{R.drawable.kylin_guid_vp1, R.drawable.kylin_guid_vp2,
            R.drawable.kylin_guid_vp3, R.drawable.kylin_guid_vp4};
    /** 指示器显示id */
    public int[] mDuidDotId = new int[]{R.drawable.kylin_guid_dot_normal_drawable,
            R.drawable.kylin_guid_dot_selected_drawable};
    public List<ImageView> mVPImgView = new ArrayList<ImageView>();
    public List<ImageView> mDotImgView = new ArrayList<ImageView>();

    /**
     * 初始化欢迎页中显示的Imgs和指示器
     * @param vp  显示的ViewPage控件
     * @param dotContainer 指示器容器
     */
    public void initData(final ViewPager vp, LinearLayout dotContainer) {
        if (mDotImgView == null) {
            mDotImgView = new ArrayList<ImageView>();
        }
        if (mDotImgView.size() >= 0) {
            mDotImgView.clear();
        }
        for (int i = 0; i < mDuidVpId.length; i++) {
            //处理指示器
            ImageView item = new ImageView(getView());
            if (i == 0) {
                item.setImageResource(mDuidDotId[1]);
            } else {
                item.setImageResource(mDuidDotId[0]);
            }
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            if (i > 0) {
                lp.leftMargin = 10;
            }
            item.setLayoutParams(lp);
            final int position = i;
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    vp.setCurrentItem(position);
                }
            });
            mDotImgView.add(item);
            dotContainer.addView(item);
            //处理显示的图片
            ImageView vpItem = new ImageView(getView());
            vpItem.setImageResource(mDuidVpId[i]);
            ViewPager.LayoutParams vpLp = new ViewPager.LayoutParams();
            vpLp.height = ViewPager.LayoutParams.MATCH_PARENT;
            vpLp.width = ViewPager.LayoutParams.MATCH_PARENT;
            vpItem.setLayoutParams(vpLp);
            vpItem.setScaleType(ImageView.ScaleType.FIT_XY);
            mVPImgView.add(vpItem);
        }
    }

    /**
     * 设置显示器
     * @param position
     */
    public void setDotSelectPostion(int position) {
        if (position >= mDotImgView.size()) {
            return;
        }
        for (int i = 0; i < mDotImgView.size(); i++) {
            mDotImgView.get(i).setImageResource(mDuidDotId[0]);
        }
        mDotImgView.get(position).setImageResource(mDuidDotId[1]);
    }

    @Override
    public String onChangeBaseUrlCall(String key) {
        return null;
    }
}
