package com.kylin.ui.guid;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.kylin.R;
import com.kylin.libs.mvpbase.BaseActivity;
import com.kylin.ui.main.MainActivity;

/**
 *  欢迎页面
 * @author Kylin
 * @date 2018/3/31
 */
public class GuidActivity extends BaseActivity<GuidPresenter, GuidModel> implements GuidContract.GuidView,
        ViewPager.OnPageChangeListener{
    private ViewPager mGuidViewPager;
    private GuidPageAdapter mGuidAdapter;
    private LinearLayout mGuidDotContainer;
    private Button mGuidStartBtn;
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        showLog("GuidActivity oncreated");
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.kylin_ui_guid_activity;
    }

    @Override
    protected void initView() {
        mGuidViewPager = findId(R.id.guid_view_page);
        mGuidDotContainer = findId(R.id.guid_dot_layout);
        mGuidStartBtn = findId(R.id.guid_start_btn);
    }

    @Override
    protected void initData() {
        getPresenter().initData(mGuidViewPager,mGuidDotContainer);
        mGuidAdapter = new GuidPageAdapter(getPresenter().mVPImgView);
    }

    @Override
    protected void initListenerAndAdapter() {
        mGuidViewPager.setAdapter(mGuidAdapter);
        mGuidViewPager.addOnPageChangeListener(this);
        mGuidStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(GuidActivity.this, MainActivity.class);
                GuidActivity.this.startActivity(intent);
                finish();
            }
        });
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
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        getPresenter().setDotSelectPostion(position);
        if (position == getPresenter().mVPImgView.size() - 1){
            mGuidStartBtn.setVisibility(View.VISIBLE);
        }else {
            mGuidStartBtn.setVisibility(View.GONE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
