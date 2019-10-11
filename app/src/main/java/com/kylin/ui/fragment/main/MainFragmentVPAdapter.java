package com.kylin.ui.fragment.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;

import com.kylin.libs.mvpbase.BaseFragment;
import com.kylin.ui.fragment.mainvp_discover.MainVPDiscoverFragment;
import com.kylin.ui.fragment.mainvp_mine.MainVPMineFragment;
import com.kylin.ui.fragment.mainvp_music_store.MainVPMusicStoreFragment;

import java.util.ArrayList;

/**
 * 主界面frament中viewpager适配器
 *
 * @author Kylin
 * @date 2018/4/4.
 */

public class MainFragmentVPAdapter extends FragmentPagerAdapter {
    private ArrayList<BaseFragment> mFragaments;
    private final ArrayList<String> mTiltes;
    private FragmentManager fm;

    public MainFragmentVPAdapter(FragmentManager fm, ArrayList<BaseFragment> mFragaments, ArrayList<String> mTiltes) {
        super(fm);
        this.mFragaments = mFragaments;
        this.mTiltes = mTiltes;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragaments.get(position);
    }

    @Override
    public int getCount() {
        return mFragaments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTiltes.get(position);
    }
}
