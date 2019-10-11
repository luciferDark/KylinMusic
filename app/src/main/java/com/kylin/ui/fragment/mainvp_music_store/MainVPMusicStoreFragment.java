package com.kylin.ui.fragment.mainvp_music_store;

import android.os.Bundle;
import android.view.View;

import com.kylin.R;
import com.kylin.libs.mvpbase.BaseFragment;

/**
 * 主界面ViewPage 音乐库
 *
 * @author Kylin
 * @date 2018/4/4.
 */
public class MainVPMusicStoreFragment extends BaseFragment<MainVPMusicStorePresenter, MainVPMusicStoreModel>
        implements MainVPMusicStoreContract.MainVPMusicStoreFragmentView {

    public MainVPMusicStoreFragment() {

    }

    public static MainVPMusicStoreFragment newInstance(String... params) {
        MainVPMusicStoreFragment fg = new MainVPMusicStoreFragment();
        Bundle args = new Bundle();
        args.putStringArray("ARGS", params);
        fg.setArguments(args);
        fg.showLog("MainVPMineFragment");
        return fg;
    }

    @Override
    protected int getLayoutResId() {
        showLog("getLayoutResId:" + R.layout.kylin_ui_main_view_page_music_store);
        return R.layout.kylin_ui_main_view_page_music_store;
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
