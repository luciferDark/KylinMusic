package com.kylin.ui.fragment.main;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.ImageButton;

import com.kylin.R;
import com.kylin.libs.mvpbase.BaseFragment;
import com.kylin.models.MusicModel;
import com.kylin.ui.fragment.mainvp_discover.MainVPDiscoverFragment;
import com.kylin.ui.fragment.mainvp_mine.MainVPMineFragment;
import com.kylin.ui.fragment.mainvp_music_store.MainVPMusicStoreFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 主界面碎片页
 *
 * @author Kylin
 * @datmodelse 2018/4/4.
 */
public class MainFragment extends BaseFragment<MainFragmentPresenter, MainFragmentModel>
        implements MainFragmentContract.MainFragmentView,
        TabLayout.OnTabSelectedListener, OnPageChangeListener {
    private TabLayout mMianFrag_TabLayout;
    private ViewPager mMainFrag_ViewPager;
    private MainFragmentVPAdapter mMianMainFragmentVPAdapter;
    private ArrayList<BaseFragment> mMianVPFragments;
    private ArrayList<String> mMianVPFragmentTitles;

    //底部播放界面
    private ViewPager mBottomMusicPlay_ViewPager;
    private MainBottomPlayVPAdapter mBottomPlayVPAdapter;
    private ImageButton mBottomMusicPlayBtn, mBottomMusicListBtn;
    private ArrayList<String> mPlayList = new ArrayList<>();

    public MainFragment() {

    }

    public static MainFragment newInstance(String... params) {
        MainFragment fg = new MainFragment();
        Bundle args = new Bundle();
        args.putStringArray("ARGS", params);
        fg.setArguments(args);
        return fg;
    }

    //----BaseFragement callback
    @Override
    protected int getLayoutResId() {
        return R.layout.kylin_ui_main_fragment;
    }

    @Override
    protected void initView(View view) {
        mMianFrag_TabLayout = findId(R.id.ui_main_fragment_app_bar_tabs);
        mMainFrag_ViewPager = findId(R.id.ui_main_fragment_view_page);

        mBottomMusicListBtn = findId(R.id.ui_main_bottom_music_play_list);
        mBottomMusicPlayBtn = findId(R.id.ui_main_bottom_music_play_control);
        mBottomMusicPlay_ViewPager = findId(R.id.ui_main_bottom_music_play_view_pager);
    }

    @Override
    protected void initData(View mView) {
        //--初始化3个主vp页面
        if (mMianVPFragments == null) {
            mMianVPFragments = new ArrayList<>();
        }
        if (mMianVPFragmentTitles == null) {
            mMianVPFragmentTitles = new ArrayList<>();
        }
        mMianVPFragments.clear();
        mMianVPFragmentTitles.clear();
        mMianVPFragments.add(MainVPMineFragment.newInstance());
        mMianVPFragmentTitles.add("我的");
        mMianVPFragments.add(MainVPMusicStoreFragment.newInstance());
        mMianVPFragmentTitles.add("音乐库");
        mMianVPFragments.add(MainVPDiscoverFragment.newInstance());
        mMianVPFragmentTitles.add("发现");
        mMianMainFragmentVPAdapter = new MainFragmentVPAdapter(getChildFragmentManager(),
                mMianVPFragments, mMianVPFragmentTitles);
        //--初始化底部播放面板VP
        mBottomPlayVPAdapter = new MainBottomPlayVPAdapter(getContext(), mPlayList);
    }

    @Override
    protected void initListenerAndAdapter(View mView) {
        mMainFrag_ViewPager.setAdapter(mMianMainFragmentVPAdapter);
        mMianFrag_TabLayout.setupWithViewPager(mMainFrag_ViewPager);
        mMianFrag_TabLayout.addOnTabSelectedListener(this);

        mBottomMusicPlay_ViewPager.setAdapter(mBottomPlayVPAdapter);
        mBottomMusicPlay_ViewPager.addOnPageChangeListener(this);
        mBottomMusicPlayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
        mBottomMusicListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLog("mBottomMusicListBtn");
                mPresenter.initBottomPlayMusicList(MainFragment.this);
            }
        });
        mPresenter.initBottomPlayMusicList(MainFragment.this);
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

    //----TabLayout callback
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        toast(tab.getText().toString());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    //----底部播放区监听器
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        showLog("onPageScrolled position：" + position);
    }

    @Override
    public void onPageSelected(int position) {
        showLog("onPageSelected position：" + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        showLog("onPageScrollStateChanged state：" + state);
    }

    @Override
    public void setAdapter(List<MusicModel> models) {
        if (mPlayList == null) {
            mPlayList = new ArrayList<>();
        }
        mPlayList.clear();
        for (MusicModel model : models) {
            String jsonStr = "{\"name\": \"" + model.getMusicName() +
                    "\",\"songer\": \"" + model.getMusicAlbum() + "_" +
                    model.getMusicSinger() + "\"}";
            mPlayList.add(jsonStr);
        }
        mBottomPlayVPAdapter.notifyDataSetChanged();
    }
}
