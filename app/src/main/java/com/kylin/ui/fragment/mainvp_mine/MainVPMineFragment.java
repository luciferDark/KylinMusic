package com.kylin.ui.fragment.mainvp_mine;

import android.os.Bundle;
import android.view.View;

import com.kylin.R;
import com.kylin.libs.mvpbase.BaseFragment;

/**
 * 主界面ViewPage 我的
 *
 * @author Kylin
 * @date 2018/4/4.
 */
public class MainVPMineFragment extends BaseFragment<MainVPMinePresenter, MainVPMineModel>
        implements MainVPMineContract.MainVPMineFragmentView {

    public MainVPMineFragment() {

    }

    public static MainVPMineFragment newInstance(String... params) {
        MainVPMineFragment fg = new MainVPMineFragment();
        Bundle args = new Bundle();
        args.putStringArray("ARGS", params);
        fg.setArguments(args);
        fg.showLog("MainVPMineFragment");
        return fg;
    }

    //----BaseFragement callback
    @Override
    protected int getLayoutResId() {
        return R.layout.kylin_ui_main_view_page_mine;
    }

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
