package com.kylin.ui.fragment.mainvp_discover;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.kylin.R;
import com.kylin.libs.mvpbase.BaseFragment;

/**
 * 主界面ViewPage 发现
 *
 * @author Kylin
 * @date 2018/4/4.
 */
public class MainVPDiscoverFragment extends BaseFragment<MainVPDiscoverPresenter, MainVPDiscoverModel>
        implements MainVPDiscoverContract.MainVPDiscoverFragmentView {

    public MainVPDiscoverFragment() {

    }

    public static MainVPDiscoverFragment newInstance(String... params) {
        MainVPDiscoverFragment fg = new MainVPDiscoverFragment();
        Bundle args = new Bundle();
        args.putStringArray("ARGS", params);
        fg.setArguments(args);
        fg.showLog("MainVPMineFragment");
        return fg;
    }

    @Override
    protected int getLayoutResId() {
        showLog("getLayoutResId:" + R.layout.kylin_ui_main_view_page_discover);
        return R.layout.kylin_ui_main_view_page_discover;
    }

    //----BaseFragement callback
    @Override
    protected void initView(View view) {
    }

    @Override
    protected void initData(View mView) {

    }

    @Override
    protected void initListenerAndAdapter(View mView) {

    }

    //---- BaseView callback
    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError() {

    }
}
