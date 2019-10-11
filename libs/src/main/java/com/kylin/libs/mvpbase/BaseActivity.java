package com.kylin.libs.mvpbase;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.kylin.libs.utils.LogUtils;
import com.kylin.libs.utils.helper.InstanceUtil;

/**
 * mvp模式基础Activity
 *
 * @author Kylin
 * @date 2018/3/30.
 */
public abstract class BaseActivity<P extends BasePresenter, M extends BaseModel> extends AppCompatActivity implements BaseView {
    public P mPresenter;
    public M mModel;

    private static final String TAG = "kylin";
    protected Context mContext = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showLog("baseActivity oncreate");
        setContentView(getLayoutResId());
        //通过反射方式实例化相关Presenter和Model
        mPresenter = InstanceUtil.instance(this, 0);
        mModel = InstanceUtil.instance(this, 1);
        mPresenter.attachModelView(mModel, this);

        mContext = this.getApplicationContext();

        initView();
        initData();
        initListenerAndAdapter();
    }

    /**
     * 获取Presenter
     *
     * @return
     */
    public P getPresenter() {
        return mPresenter;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mPresenter) {
            //处理Presenter相关生命周期
            mPresenter.onStop();
            mPresenter.dettach();
        }
    }

    @Override
    public Context getContext() {
        return mContext;
    }

    /**
     * 获取布局id
     *
     * @return 布局id
     */
    protected abstract int getLayoutResId();

    /**
     * 初始化view空间操作
     */
    protected abstract void initView();

    /**
     * 初始化相关数据
     */
    protected abstract void initData();

    /**
     * 初始化相关监听器和适配器
     */
    protected abstract void initListenerAndAdapter();


    /**
     * 泛型获取viewId
     *
     * @param id
     * @param <T>
     * @return
     */
    protected <T extends View> T findId(@NonNull @IdRes int id) {
        return (T) findViewById(id);
    }

    /**
     * 显示log日志 方便子类调用
     *
     * @param msg
     */
    protected void showLog(String msg) {
        LogUtils.debug("===>" + this.getClass().getName() + "===>" + msg);
    }

    /**
     * 显示log日志 方便子类调用
     *
     * @param msg
     */
    protected void toast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

}
