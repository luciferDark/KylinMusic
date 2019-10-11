package com.kylin.ui.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

import com.kylin.R;
import com.kylin.libs.mvpbase.BaseActivity;
import com.kylin.ui.fragment.main.MainFragment;

/**
 * 主界面
 *
 * @author Kylin
 */
public class MainActivity extends BaseActivity<MainPresenter, MainModel>
        implements MainContract.MainView,
        NavigationView.OnNavigationItemSelectedListener {
    private NavigationView mDrawNavigationView;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showLog("MainActivity onCreate: ");
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.kylin_ui_main_activity;
    }

    @Override
    protected void initView() {
        mDrawerLayout = findId(R.id.ui_main_drawer_layout);
        mDrawNavigationView = findId(R.id.ui_main_nav_view);
    }

    @Override
    protected void initData() {
        mDrawNavigationView.setItemIconTintList(null);
        mDrawNavigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void initListenerAndAdapter() {
        initMainFragment();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError() {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        item.setCheckable(true);
        toast(item.getTitle().toString());
        int groupId = item.getGroupId();
        int itemId = item.getItemId();
        mDrawerLayout.closeDrawers();
        return false;
    }

    public void initMainFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.ui_main_fragment_container, MainFragment.newInstance(),
                        "MainFragement")
                .commit();
    }
}
