package com.kylin.ui.guid;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 *欢迎页面Viewpager适配器
 * @author Kylin
 * @date 2018/4/1
 */

public class GuidPageAdapter<T> extends PagerAdapter {
    private List<T> mDates;

    public GuidPageAdapter(List<T> mDates) {
        this.mDates = mDates;
    }

    @Override
    public int getCount() {
        return mDates.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView((View) mDates.get(position));
        return mDates.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) mDates.get(position));
    }
}
