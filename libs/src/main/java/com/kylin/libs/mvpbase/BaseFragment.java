package com.kylin.libs.mvpbase;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.kylin.libs.utils.helper.InstanceUtil;

/**
 * mvp模式基础Fragment
 *
 * @author Kylin
 * @date 2018/3/30.
 */
public abstract class BaseFragment<P extends BasePresenter, M extends BaseModel> extends Fragment implements BaseView {
    public P mPresenter;
    public M mModel;

    private static final String TAG = "kylin";
    protected View mView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutResId(), container, false);
        mPresenter = InstanceUtil.instance(this, 0);
        mModel = InstanceUtil.instance(this, 1);
        initView(mView);

        initData(mView);
        initListenerAndAdapter(mView);
        showLog("onCreateView");
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (null != mPresenter) {
            mPresenter.attachModelView(mModel, this);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (null != mPresenter) {
            mPresenter.onStop();
            mPresenter.dettach();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
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
    protected abstract void initView(View view);

    /**
     * 初始化相关数据
     * @param mView
     */
    protected abstract void initData(View mView);

    /**
     * 初始化相关监听器和适配器
     * @param mView
     */
    protected abstract void initListenerAndAdapter(View mView);

    /**
     * 泛型获取viewId
     *
     * @param id
     * @param <T>
     * @return
     */
    protected <T extends View> T findId(@NonNull @IdRes int id) {
        if (mView == null){
            return  null;
        }
        return (T) mView.findViewById(id);
    }

    protected void showLog(String msg) {
        Log.d(TAG, "===>" + this.getClass().getName() + "===>" + msg);
    }

    /**
     * 显示log日志 方便子类调用
     * @param msg
     */
    protected void toast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
        showLog(msg);
    }
}
